package com.team5.projrental.product;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.aop.anno.CountView;
import com.team5.projrental.common.aop.category.CountCategory;
import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.exception.base.*;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.product.model.*;
import com.team5.projrental.product.model.proc.*;
import com.team5.projrental.product.model.review.ReviewGetDto;
import com.team5.projrental.product.model.review.ReviewResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.team5.projrental.common.Const.*;
import static com.team5.projrental.common.exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CleanProductService implements RefProductService {

    private final RefProductRepository productRepository;

    private final AxisGenerator axisGenerator;

    private final AuthenticationFacade authenticationFacade;

    private final MyFileUtils myFileUtils;


    /*
        ------- Logic -------
     */

    /**
     * 해당 카테고리의 전체 제품 조회.
     *
     * @param sort
     * @param search
     * @param icategory
     * @return List<ProductListVo>
     */
    public List<ProductListVo> getProductList(Integer sort,
                                              String search,
                                              int imainCategory,
                                              int isubCategory,
                                              int page,
                                              int prodPerPage) {
        // page 는 페이징에 맞게 변환되어 넘어옴.

        // iuser 가져오기 -> isLiked 를 위해서
        Integer iuser;
        try {
            iuser = getLoginUserPk();
        } catch (ClassCastException ignored) {
            iuser = null;
        }
        Categories icategory = new Categories(imainCategory, isubCategory);
        // 카테고리 검증
        CommonUtils.ifCategoryNotContainsThrow(icategory);


        // search 의 length 가 2 이상으로 validated 되었으므로 문제 없음.
        List<GetProductListResultDto> products =
                productRepository.findProductListBy(new GetProductListDto(sort, search, icategory, page, iuser, prodPerPage));
        // 결과물 없음 여부 체크 (결과물 없으면 빈 객체 리턴)
        if (!CommonUtils.checkNullOrZeroIfCollectionReturnFalse(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE,
                products)) return new ArrayList<>();

        // 검증 이상 무
        return products.stream().map(ProductListVo::new).toList();
    }

    public List<ProductListVo> getProductListForMain(
            List<Integer> imainCategory,
            List<Integer> isubCategory
    ) {

        int page = 0;
        int limit = Const.MAIN_PROD_PER_PAGE;
        List<ProductListVo> result = new ArrayList<>();
        for (int i = 0; i < imainCategory.size(); i++) {
            result.addAll(getProductList(null, null, imainCategory.get(i), isubCategory.get(i), page, limit));
        }


        return result;
    }

    /**
     * 선택한 특정 제품페이지 조회.
     *
     * @param iproduct
     * @return ProductVo
     */
    @CountView(CountCategory.PRODUCT)
    public ProductVo getProduct(int imainCategory, int isubCategory, Integer iproduct) {

        Categories icategory = new Categories(imainCategory, isubCategory);

        // 카테고리 검증
        CommonUtils.ifCategoryNotContainsThrow(icategory);

        // 사진을 '제외한' 모든 정보 획득 & 제공된 카테고리 검증 (category -> icategory)
        GetProductResultDto productBy = productRepository.findProductBy(
                new GetProductBaseDto(icategory, iproduct, getLoginUserPk())
        );

        // 결과물 없음 여부 검증
        // iproduct 가 존재하는 제품이라는것 보장됨.
        CommonUtils.ifAnyNullThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, productBy);
        // 검증 완

        // 사진, 리뷰, 거래 불가능 날짜를 가져오기 위한 iproduct 획득
        Integer productPK = productBy.getIproduct();
        // 리턴 객체 미리 생성
        ProductVo result = new ProductVo(productBy);

        // 리뷰
        List<ReviewResultVo> reviews = getReview(productPK, 1, IN_PRODUCT_REVIEW_PER_PAGE);
        result.setReviews(reviews);

        //거래 불가능 날짜 가져오기
        result.setDisabledDates(getDisabledDates(productPK, LocalDate.now()));

        // 사진
        List<PicsInfoVo> ectPics = productRepository.findPicsBy(productPK);

        // 사진 조회 결과가 없으면 곧바로 리턴
        if (ectPics == null || ectPics.isEmpty()) {
            return result;
        }

        result.setProdSubPics(ectPics);
        return result;
    }


    /**
     * 제품 & 제품 사진 등록<br><br>
     * pics + mainPic 개수 검증 - 10개 이하 -> iuser 가 존재하는지 검증 -> category 존재여부 검증 ->
     * price 양수 검증(@V) -> buyDate 오늘보다 이전인지 검증 -> depositPer 70 이상 100 이하 검증(@V) ->
     * 오늘이 rentalStartDate 보다 이전이 아닌지 검증 -> rentalEndDate 가 rentalStartDate 보다 이전이 아닌지 검증
     *
     * @param dto
     * @return ResVo
     */
    @Transactional
    public ResVo postProduct(MultipartFile mainPic, List<MultipartFile> pics, ProductInsDto dto) {

        // 욕설 포함시 예외 발생
        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getTitle(), dto.getContents(), dto.getRestAddr());

        CommonUtils.ifAllNullThrow(BadMainPicException.class, BAD_PIC_EX_MESSAGE, mainPic);

        // 사진 개수 검증 - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)

        dto.setIuser(getLoginUserPk());

        // 카테고리 검증 - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        CommonUtils.ifCategoryNotContainsThrow(dto.getIcategory());

        // 날짜 검증 시작  - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        CommonUtils.ifAfterThrow(
                BadDateInfoException.class, BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE,
                LocalDate.now(), dto.getBuyDate()
        );
        CommonUtils.ifBeforeThrow(
                BadDateInfoException.class, RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE,
                dto.getRentalStartDate(), LocalDate.now()
        );
        CommonUtils.ifBeforeThrow(BadDateInfoException.class, RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE
                , dto.getRentalEndDate(), dto.getRentalStartDate());
        // 날짜 검증 끝


        // logic
        // 주소 검증
        Addrs addrs = axisGenerator.getAxis(dto.getAddr());
        // insert 할 객체 준비 완.
        InsProdBasicInfoDto insProdBasicInfoDto = new InsProdBasicInfoDto(dto, addrs.getAddress_name(), Double.parseDouble(addrs.getX()),
                Double.parseDouble(addrs.getY()), dto.getInventory());
        try {
            if (productRepository.saveProduct(insProdBasicInfoDto) == 1) {
                // 사진은 완전히 따로 저장해야함 (useGeneratedKey)

                // 프로필 사진 저장
                productRepository.updateProduct(ProductUpdDto.builder()
                        .storedMainPic(
                                myFileUtils.savePic(
                                        mainPic, CATEGORY_PRODUCT_MAIN, String.valueOf(insProdBasicInfoDto.getIproduct()))
                        )
                        .iproduct(insProdBasicInfoDto.getIproduct())
                        .iuser(dto.getIuser())
                        .build()
                );

                // 그 외 사진 저장
                if (pics != null && !pics.isEmpty()) {
                    // pics 에 insert 할 객체
                    InsProdPicsDto insProdPicsDto = new InsProdPicsDto(insProdBasicInfoDto.getIproduct(),
                            myFileUtils.savePic(pics, CATEGORY_PRODUCT_SUB, String.valueOf(insProdBasicInfoDto.getIproduct())));
                    if (productRepository.savePics(insProdPicsDto) == 0)
                        throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
                }
                return new ResVo(insProdBasicInfoDto.getIproduct());
            }
        } catch (FileNotContainsDotException e) {
            throw new BadMainPicException(BAD_PIC_EX_MESSAGE);
        }
        throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
    }


    /**
     * 제품 정보 수정 <br>
     *
     * @param dto
     * @return ResVo
     */
    @Transactional
    public ResVo putProduct(MultipartFile mainPic, List<MultipartFile> pics, ProductUpdDto dto) {

        // 수정할 모든 데이터가 null 이면 예외
        CommonUtils.ifAllNullThrow(BadInformationException.class, ALL_INFO_NOT_EXISTS_EX_MESSAGE,
                dto.getIcategory(), dto.getAddr(),
                dto.getRestAddr(), dto.getTitle(),
                dto.getContents(), dto.getPrice(),
                dto.getRentalPrice(), dto.getDeposit(),
                dto.getBuyDate(), dto.getRentalStartDate(),
                dto.getRentalEndDate(), dto.getDelPics(),
                dto.getInventory(), mainPic, pics);


        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getTitle() == null ? "" : dto.getTitle(),
                dto.getContents() == null ? "" : dto.getContents(),
                dto.getRestAddr() == null ? "" : dto.getRestAddr());

        // 삭제사진 필요시 삭제
        List<String> delPicsPath = null;
        boolean flag = false;
        if (dto.getDelPics() != null && !dto.getDelPics().isEmpty()) {
            // 실제 사진 삭제를 위해 사진 경로 미리 가져오기
            delPicsPath = productRepository.getPicsAllBy(dto.getDelPics());
            if (delPicsPath.isEmpty()) throw new BadInformationException(BAD_INFO_EX_MESSAGE);
            // 사진 삭제 in db
            if (productRepository.deletePics(dto.getIproduct(), dto.getDelPics()) == 0) {
                throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
            }
            // 실제 사진 삭제는 마지막에 에러가 없으면 실행. (유예)
            // flag 가 true 면 파일을 삭제할것임.
            flag = true;
        }
        // 병합하지 않아도 되는 데이터 검증

        int loginUserPk = getLoginUserPk();
        // 카테고리 검증
        if (dto.getIcategory() != null) {
            CommonUtils.ifCategoryNotContainsThrow(dto.getIcategory());
        }

        // db에서 기존 데이터들 가져오기
        UpdProdBasicDto fromDb =
                productRepository.findProductByForUpdate(new GetProductBaseDto(dto.getIproduct(), loginUserPk));
        CommonUtils.ifAnyNullThrow(BadProductInfoException.class, BAD_PRODUCT_INFO_EX_MESSAGE,
                fromDb);
        // 메인사진 수정시
        boolean mainPicFlag = mainPic != null;
        String mainPicPath = null;
        if (mainPicFlag) {
            mainPicPath = fromDb.getStoredPic();
        }

        // 병합
        Integer price = dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice();
        UpdProdBasicDto mergedData = new UpdProdBasicDto(
                dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice(),
                getDepositFromPerByUtils(dto, fromDb),
                dto.getBuyDate() == null ? fromDb.getBuyDate() : dto.getBuyDate(),
                dto.getRentalStartDate() == null ? fromDb.getRentalStartDate() : dto.getRentalStartDate(),
                dto.getRentalEndDate() == null ? fromDb.getRentalEndDate() : dto.getRentalEndDate()
        );
        int dbPicsCountAfterDelPics = productRepository.findPicsCount(dto.getIproduct());

        // 사진 개수 검증
        // fromDb 사진(이미 삭제필요한 사진은 삭제 된 상태) 과 dto.getPics 를 savePic 하고난 결과를 합쳐서 개수 체크.
        if (pics != null && !pics.isEmpty()) {
            CommonUtils.checkSizeIfOverLimitNumThrow(IllegalProductPicsException.class, ILLEGAL_PRODUCT_PICS_EX_MESSAGE,
                    pics.stream(), 9 - dbPicsCountAfterDelPics);
        }

        // 데이터 검증
        // 날짜 검증 시작  - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        CommonUtils.ifAfterThrow(
                BadDateInfoException.class, BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE,
                LocalDate.now(), mergedData.getBuyDate()
        );
        CommonUtils.ifBeforeThrow(BadDateInfoException.class, RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE
                , mergedData.getRentalEndDate(), mergedData.getRentalStartDate());
        // 날짜 검증 끝

        // 주소 검증
        Addrs addrs = null;
        if (dto.getAddr() != null) {
            addrs = axisGenerator.getAxis(dto.getAddr());
        }

        // 문제 없음.


        try {
            // 문제 없으면 추가 사진 insert
            if (pics != null && !pics.isEmpty()) {
                if (productRepository.savePics(new InsProdPicsDto(dto.getIproduct(),
                        myFileUtils.savePic(pics, CATEGORY_PRODUCT_SUB, String.valueOf(dto.getIproduct())))) == 0) {
                    throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
                }
            }


            // update 할 객체 세팅
            dto.setIuser(loginUserPk);
            dto.setStoredMainPic(mainPic == null ? null : myFileUtils.savePic(mainPic, CATEGORY_PRODUCT_MAIN,
                    String.valueOf(dto.getIproduct())));
            dto.setDeposit(dto.getDepositPer() == null ? null : CommonUtils.getDepositFromPer(price, dto.getDepositPer()));
            if (addrs != null) {
                dto.setX(Double.parseDouble(addrs.getX()));
                dto.setY(Double.parseDouble(addrs.getY()));
            }
        } catch (FileNotContainsDotException e) {
            throw new BadMainPicException(BAD_PIC_EX_MESSAGE);
        }
        // do update
        if (CommonUtils.ifAllNullReturnFalse(
                dto.getIcategory(), dto.getAddr(),
                dto.getRestAddr(), dto.getTitle(),
                dto.getContents(), dto.getPrice(),
                dto.getRentalPrice(), dto.getDeposit(),
                dto.getBuyDate(), dto.getRentalStartDate(),
                dto.getRentalEndDate(), dto.getInventory(),
                dto.getStoredMainPic(),
                dto.getX(), dto.getY())) {
            if (productRepository.updateProduct(dto) == 0) {
                throw new BadProductInfoException(BAD_PRODUCT_INFO_EX_MESSAGE);
            }
        }
        // 유예된 사진파일 실제로 삭제
        if (mainPicFlag) {
            myFileUtils.delCurPic(mainPicPath);
        }
        if (flag) { // flag 가 true 라는것은 dto.getDelPics() 에 값이 있다는것.
            // 실제 사진 삭제
            delPicsPath.forEach(myFileUtils::delCurPic);
        }

        return new ResVo(SUCCESS);
    }


    /**
     * 게시물 삭제<br>
     * 실제로 삭제하지는 않고, 상태를 변경. <br><br><br>
     * 상태설명:<br>
     * 0: 활성화 상태 (제품 insert 시 default 값)<br>
     * -1: 삭제<br>
     * -2: 숨김<br><br><br>
     * <p>
     * 로직:<br>
     * 삭제를 거래중이면 불가능하게? -> 별로인듯 함 그냥 -1 처리 해 두기만 하면 판매자 정보를 join 으로 조회할 수 있음. <br><br>
     * <p>
     * div * -1 로 istatus 값 세팅 <br>
     * -1 -> -1이 아닌곳을 -1 로, <br>
     * -2 -> 0인 곳을 -2 로 <br><br>
     * <p>
     * 결과가 0 이면 -> 잘못된 정보 기입됨. ex 발생.
     *
     * @param iproduct
     * @param div
     * @return ResVo
     */
    @Transactional
    public ResVo delProduct(Integer iproduct, Integer div) {
        CommonUtils.ifFalseThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE,
                productRepository.findIproductCountBy(iproduct));
        if (productRepository.updateProductStatus(new DelProductBaseDto(iproduct, getLoginUserPk(), div * -1)) == 0) {
            throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        }
        // 삭제시 성공했으니 불필요한 리소스는 모두 지우기
        if (div == 1) {
            List<String> delPicPaths = new ArrayList<>();
            delPicPaths.add(productRepository.findMainPicPathForDelBy(iproduct));
            delPicPaths.addAll(productRepository.findSubPicsPathForDelBy(iproduct));
            delPicPaths.forEach(p -> {
                String separator = p.contains("/") ? "/" : p.contains("\\") ? "\\" : p.contains("\\\\") ? "\\\\" : null;
                if (separator == null) return;
                myFileUtils.delFolderTrigger(p.substring(0, p.lastIndexOf(separator)));
            });
        }
        return new ResVo(SUCCESS);
    }

    public List<ProductUserVo> getUserProductList(Integer iuser, Integer page) {
        List<GetProductListResultDto> productListBy =
                productRepository.findProductListBy(new GetProductListDto(
                        iuser == null ? getLoginUserPk() : iuser, page
                ));
        CommonUtils.checkNullOrZeroIfCollectionThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, productListBy);

        return productListBy.stream().map(ProductUserVo::new).toList();

    }

    public List<ReviewResultVo> getAllReviews(Integer iproduct, Integer page) {
        CommonUtils.ifFalseThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE,
                productRepository.findIproductCountBy(iproduct));
        return getReview(iproduct, page, TOTAL_REVIEW_PER_PAGE);
    }

    public List<LocalDate> getDisabledDate(Integer iproduct, Integer y, Integer m) {
        return getDisabledDates(iproduct, LocalDate.of(y, m, 1));
    }

    /*
        ------- Inner Methods -------
     */

    /**
     * 제품에 포함하여 리뷰를 가져오거나,
     * 해당 제품의 전체 리뷰를 가져올 수 있음.
     * <p>
     * 제품에 포함할때는 page = 1, reviewPerPage 는 Const.inProductReviewPerPage 사용.
     * 전체를 가져올때는 넘어온 page, reviewPerPage 는 Const.totalReviewPerPage 사용.
     *
     * @param iproduct
     * @param page
     * @param reviewPerPage
     * @return List<ReviewResultVo>
     */
    private List<ReviewResultVo> getReview(Integer iproduct, Integer page, Integer reviewPerPage) {
        CommonUtils.ifAnyNullThrow(NotEnoughInfoException.class, CAN_NOT_BLANK_EX_MESSAGE,
                iproduct, page, reviewPerPage);
        return productRepository.getReview(new ReviewGetDto(iproduct, (page - 1) * reviewPerPage, reviewPerPage));
    }

    private int getLoginUserPk() {
        return authenticationFacade.getLoginUserPk();
    }

    private Integer getDepositFromPerByUtils(ProductUpdDto dto, UpdProdBasicDto fromDb) {
        return CommonUtils.getDepositFromPer(
                dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice(),
                // 가격이나 보증금퍼센트가 변경되면 변경된 보증금 가격으로 바꾸기 위한 로직
                dto.getDepositPer() == null ?
                        CommonUtils.getDepositPerFromPrice(dto.getPrice() == null ?
                                        fromDb.getPrice() :
                                        dto.getPrice(),
                                fromDb.getDeposit()) :
                        dto.getDepositPer());
    }

    private List<LocalDate> getDisabledDates(int iproduct, LocalDate refStartDate) {
        LocalDate forRefEndDate = refStartDate.plusMonths(ADD_MONTH_NUM_FOR_DISABLED_DATE);
        return getDisabledDates(iproduct, refStartDate, LocalDate.of(forRefEndDate.getYear(), forRefEndDate.getMonth(),
                forRefEndDate.lengthOfMonth()));
    }

    private List<LocalDate> getDisabledDates(int iproduct, LocalDate refStartDate, LocalDate refEndDate) {

        final Integer stockCount = productRepository.findStockCountBy(iproduct);
        CommonUtils.ifAnyNullThrow(BadProductInfoException.class, BAD_PRODUCT_INFO_EX_MESSAGE,
                stockCount);
        List<CanNotRentalDateVo> disabledRefDates =
                productRepository.findDisabledDatesBy(new CanNotRentalDateDto(iproduct, refStartDate, refEndDate));
        // 만약 해당 월들 사이에 이미 거래중인 건이 없다면 곧바로 빈 배열 리턴.
        if (disabledRefDates == null || disabledRefDates.isEmpty()) return new ArrayList<>();

        // 거래 불가능한 날짜들 담을 객체 미리 생성
        List<LocalDate> disabledDates = new ArrayList<>();

        // 검사 시작일부터 하루씩 더해질 객체 생성
        LocalDate dateWalker = LocalDate.of(refStartDate.getYear(), refStartDate.getMonth(), refStartDate.getDayOfMonth());
        // 작업 시작

        while (!dateWalker.isAfter(refEndDate)) {
            LocalDate lambdaDateWalker = dateWalker;
            if (disabledRefDates.stream().filter(
                    d -> lambdaDateWalker.isEqual(d.getRentalEndDate()) ||
                            lambdaDateWalker.isEqual(d.getRentalStartDate()) ||
                            lambdaDateWalker.isBefore(d.getRentalEndDate()) && lambdaDateWalker.isAfter(d.getRentalStartDate()
                            )
            ).count() >= stockCount) {
                disabledDates.add(LocalDate.of(dateWalker.getYear(),
                        dateWalker.getMonth(),
                        dateWalker.getDayOfMonth()));
            }
            dateWalker = dateWalker.plusDays(ADD_A);
        }

        return disabledDates;
    }

}
