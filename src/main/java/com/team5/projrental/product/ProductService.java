package com.team5.projrental.product;

import com.team5.projrental.common.aop.anno.CountView;
import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.exception.base.BadDateInfoException;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.exception.base.BadProductInfoException;
import com.team5.projrental.common.exception.base.WrapRuntimeException;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.product.model.*;
import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import com.team5.projrental.product.model.proc.*;
import com.team5.projrental.common.security.AuthenticationFacade;
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
public class ProductService {


    private final ProductRepository productRepository;

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
                                              int icategory,
                                              int page) {
        // page 는 페이징에 맞게 변환되어 넘어옴.

        // sort 검증
        if (sort != null && sort != 1 && sort != 2) throw new BadInformationException(BAD_SORT_EX_MESSAGE);
        // 카테고리 검증
        GetProductListDto getProductListDto = new GetProductListDto(sort, search, icategory, page);
        List<GetProductListResultDto> products = productRepository.findProductListBy(getProductListDto);
        // 결과물 없음 여부 체크
        CommonUtils.checkNullOrZeroIfCollectionThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, products);

        // 검증 이상 무
        List<ProductListVo> result = new ArrayList<>();
        products.forEach(product -> {
            ProductListVo productListVo = new ProductListVo(product);

            result.add(productListVo);
        });

        return result;
    }

    /**
     * 선택한 특정 제품페이지 조회.
     *
     * @param iproduct
     * @return ProductVo
     */
    @CountView
    public ProductVo getProduct(Integer icategory, Integer iproduct) {

        // 사진을 '제외한' 모든 정보 획득 & 제공된 카테고리 검증 (category -> icategory)
        GetProductResultDto productBy = productRepository.findProductBy(
                new GetProductBaseDto(icategory, iproduct)
        );
        // 결과물 없음 여부 검증
        CommonUtils.ifAnyNullThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, productBy);

        // 검증 완
        Integer productPK = productBy.getIproduct();
        List<GetProdEctPicDto> ectPics = productRepository.findPicsBy(productPK);

        ProductVo result = new ProductVo(productBy);
        // 사진 조회 결과가 없으면 곧바로 리턴
        if (ectPics == null || ectPics.isEmpty()) {
            return result;
        }

        List<String> resultEctPic = new ArrayList<>();
        ectPics.forEach(p -> resultEctPic.add(p.getProdStoredPic()));
        result.setProdPics(resultEctPic);
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
        /* TODO: 2024-01-10
            security 적용시 모든 iuser 가 있는부분 로직 변경, 모델 변경 해야함.
            --by Hyunmin */

        if (mainPic != null) dto.setMainPic(mainPic);
        if (pics != null) dto.setPics(pics);

        // 사진 개수 검증 - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        if (dto.getPics() != null) {
            CommonUtils.checkSizeIfOverLimitNumThrow(IllegalProductPicsException.class, ILLEGAL_PRODUCT_PICS_EX_MESSAGE,
                    dto.getPics().stream(), 9);
        }

        // iuser 있는지 체크
        dto.setIuser(authenticationFacade.getLoginUserPk());
        CommonUtils.ifFalseThrow(NoSuchUserException.class, NO_SUCH_USER_EX_MESSAGE, productRepository.findIuserCountBy(dto.getIuser()));

        // 카테고리 검증 - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        CommonUtils.ifCategoryNotContainsThrowOrReturn(dto.getIcategory());

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
        Addrs addrs = axisGenerator.getAxis(dto.getAddr().concat(dto.getRestAddr()));
        // insert 할 객체 준비 완.
        InsProdBasicInfoDto insProdBasicInfoDto = new InsProdBasicInfoDto(dto, addrs.getAddress_name(), Double.parseDouble(addrs.getX()),
                Double.parseDouble(addrs.getY()));
        try {


            if (productRepository.saveProduct(insProdBasicInfoDto) == 1) {
                if (dto.getPics() != null && !dto.getPics().isEmpty()) {
                    insProdBasicInfoDto.setStoredPic(myFileUtils.savePic(dto.getMainPic(), CATEGORY_PRODUCT_MAIN,
                            String.valueOf(insProdBasicInfoDto.getIproduct())));
                    // pics 에 insert 할 객체
                    InsProdPicsDto insProdPicsDto = new InsProdPicsDto(insProdBasicInfoDto.getIproduct(),
                            myFileUtils.savePic(dto.getPics(), CATEGORY_PRODUCT_SUB, String.valueOf(insProdBasicInfoDto.getIproduct())));
                    if (productRepository.savePics(insProdPicsDto) == 0) throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
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
        if (mainPic != null) dto.setMainPic(mainPic);
        if (pics != null) dto.setPics(pics);

        // 수정할 모든 데이터가 null 이면 예외
        CommonUtils.ifAllNullThrow(BadInformationException.class, ALL_INFO_NOT_EXISTS_EX_MESSAGE,
                dto.getIcategory(), dto.getAddr(),
                dto.getRestAddr(), dto.getTitle(),
                dto.getContents(), dto.getMainPic(),
                dto.getPics(), dto.getPrice(),
                dto.getRentalPrice(), dto.getDeposit(),
                dto.getBuyDate(), dto.getRentalStartDate(),
                dto.getRentalEndDate(), dto.getDelPics());

        // 삭제사진 필요시 삭제
        if (dto.getDelPics() != null && !dto.getDelPics().isEmpty()) {
            if (productRepository.deletePics(dto.getIproduct(), dto.getDelPics()) == 0) {
                throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
            }
        }
        // 병합하지 않아도 되는 데이터 검증


        dto.setIuser(authenticationFacade.getLoginUserPk());
        // 카테고리 검증
        CommonUtils.ifCategoryNotContainsThrowOrReturn(dto.getIcategory());
        UpdProdBasicDto fromDb =
                productRepository.findProductByForUpdate(new GetProductBaseDto(dto.getIcategory(), dto.getIproduct(), dto.getIuser()));

        // 가격이나 보증금퍼센트가 변경되면 변경된 보증금 가격으로 바꾸기 위한 로직
        Integer resolvedDepositPer = CommonUtils.getDepositPerFromPrice(dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice(),
                dto.getDepositPer() == null ? fromDb.getDepositPer() : dto.getDepositPer());
        // 병합
        Integer price = dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice();
        UpdProdBasicDto mergedData = new UpdProdBasicDto(
                dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice(),
                CommonUtils.getDepositFromPer(dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice(),
                        dto.getDepositPer() == null ? resolvedDepositPer : dto.getDepositPer()),
                dto.getBuyDate() == null ? fromDb.getBuyDate() : dto.getBuyDate(),
                dto.getRentalStartDate() == null ? fromDb.getRentalStartDate() : dto.getRentalStartDate(),
                dto.getRentalEndDate() == null ? fromDb.getRentalEndDate() : dto.getRentalEndDate(),
                dto.getDepositPer() == null ? resolvedDepositPer : dto.getDepositPer()
        );
        int dbPicsCount = productRepository.findPicsCount(dto.getIproduct());

        // 사진 세팅
//        if (!dto.getPics().isEmpty() && !fromDb.getPics().isEmpty()) {
//            List<StoredFileInfo> dtoPics = savePic(dto.getPics());
//            dtoPics.addAll(fromDb.getPics());
//            mergedData.setPics(dtoPics);
//        }
//        if (!dto.getPics().isEmpty() && fromDb.getPics() == null) {
//            fromDb.setPics(savePic(dto.getPics()));
//        }

        // 사진 개수 검증
        // fromDb 사진(이미 삭제필요한 사진은 삭제 된 상태) 과 dto.getPics 를 savePic 하고난 결과를 합쳐서 개수 체크.
        if (dto.getPics() != null && !dto.getPics().isEmpty()) {
            CommonUtils.checkSizeIfOverLimitNumThrow(IllegalProductPicsException.class, ILLEGAL_PRODUCT_PICS_EX_MESSAGE,
                    dto.getPics().stream(), 9 - dbPicsCount);
        }


        //

        // 데이터 검증
        // 날짜 검증 시작  - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        CommonUtils.ifAfterThrow(
                BadDateInfoException.class, BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE,
                LocalDate.now(), mergedData.getBuyDate()
        );
        CommonUtils.ifBeforeThrow(
                BadDateInfoException.class, RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE,
                mergedData.getRentalStartDate(), LocalDate.now()
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
            if (dto.getPics() != null && !dto.getPics().isEmpty()) {
                if (productRepository.savePics(new InsProdPicsDto(dto.getIproduct(),
                        myFileUtils.savePic(dto.getPics(), CATEGORY_PRODUCT_SUB, String.valueOf(dto.getIproduct())))) == 0) {
                    throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
                }
            }


            // update 할 객체 세팅
            dto.setStoredMainPic(dto.getMainPic() == null ? null : myFileUtils.savePic(dto.getMainPic(), CATEGORY_PRODUCT_MAIN,
                    String.valueOf(dto.getIproduct())));
            dto.setDeposit(dto.getDepositPer() == null ? null : CommonUtils.getDepositFromPer(price, dto.getDepositPer()));
            if (dto.getAddr() != null && dto.getRestAddr() != null && addrs != null) {
                dto.setX(Double.parseDouble(addrs.getX()));
                dto.setY(Double.parseDouble(addrs.getY()));
            }
        } catch (FileNotContainsDotException e) {
            throw new BadMainPicException(BAD_PIC_EX_MESSAGE);
        }
        // do update
        if (productRepository.updateProduct(dto) == 0) {
            throw new BadProductInfoException(BAD_PRODUCT_INFO_EX_MESSAGE);
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
    public ResVo delProduct(Integer iproduct, Integer div) {
        int iuser = authenticationFacade.getLoginUserPk();
        DelProductBaseDto delProductBaseDto = new DelProductBaseDto(iproduct, iuser, div * -1);
        if (productRepository.updateProductStatus(delProductBaseDto) == 0) {
            throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        }

        return new ResVo(SUCCESS);
    }

    public List<ProductUserVo> getUserProductList(Integer page) {
        int iuser = authenticationFacade.getLoginUserPk();
        List<GetProductListResultDto> productListBy = productRepository.findProductListBy(new GetProductListDto(iuser, page));
        CommonUtils.checkNullOrZeroIfCollectionThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, productListBy);

        List<ProductUserVo> result = new ArrayList<>();
        productListBy.forEach(product -> {
            ProductUserVo productListVo = new ProductUserVo(product);

            result.add(productListVo);
        });

        return result;

    }









    /*
        ------- Extracted Method -------
     */


//    private Integer checkAddrInDb(String addr) {
//        List<Integer> addrBy = productRepository.findAddrBy(CommonUtils.subEupmyun(addr));
//        if (addrBy.isEmpty()) throw new BadAddressInfoException(BAD_ADDRESS_INFO_EX_MESSAGE);
//        CommonUtils.checkSizeIfOverLimitNumThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
//                addrBy.stream(), 1);
//        return addrBy.get(0);
//    }


}
