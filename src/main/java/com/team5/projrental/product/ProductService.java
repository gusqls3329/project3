package com.team5.projrental.product;

import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.product.model.*;
import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import com.team5.projrental.product.model.proc.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

import static com.team5.projrental.common.Const.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    /* TODO: 1/11/24
        페이징
        --by Hyunmin */

    private final ProductRepository productRepository;

    /*
        ------- Logic -------
     */

    /**
     * 해당 카테고리의 전체 제품 조회.
     *
     * @param sort
     * @param search
     * @param category
     * @return List<ProductListVo>
     */
    public List<ProductListVo> getProductList(Integer sort,
                                              String search,
                                              String category) {
        // sort 검증
        if (sort != 1 && sort != 2) throw new BadInformationException(BAD_SORT_EX_MESSAGE);
        // 카테고리 검증
        GetProductListDto getProductListDto = new GetProductListDto(sort, search,
                CommonUtils.ifCategoryNotContainsThrowOrReturn(category));
        List<GetProductListResultDto> products = productRepository.findProductListBy(getProductListDto);
        // 결과물 없음 여부 체크
        CommonUtils.checkNullOrZeroIfCollection(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, products);

        // 검증 이상 무
        List<ProductListVo> result = new ArrayList<>();
        products.forEach(product -> {
            ProductListVo productListVo = new ProductListVo(product);

            productListVo.setUserPic(
                    CommonUtils.getPic(new StoredFileInfo(product.getUserRequestPic(), product.getUserStoredPic()))
            );
            productListVo.setProdPic(
                    CommonUtils.getPic(new StoredFileInfo(product.getProdMainRequestPic(), product.getProdMainStoredPic()))
            );
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
    public ProductVo getProduct(String category, Integer iproduct) {

        // 사진을 '제외한' 모든 정보 획득 & 제공된 카테고리 검증 (category -> icategory)
        GetProductResultDto productBy = productRepository.findProductBy(
                new GetProductBaseDto(CommonUtils.ifCategoryNotContainsThrowOrReturn(category), iproduct)
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

        List<PicSet> resultEctPic = new ArrayList<>();
        ectPics.forEach(p -> resultEctPic.add(new PicSet(CommonUtils.getPic(new StoredFileInfo(p.getProdRequestPic(), p.getProdStoredPic())), p.getIpics())));
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
    public ResVo postProduct(ProductInsDto dto) {
        /* TODO: 2024-01-10
            security 적용시 모든 iuser 가 있는부분 로직 변경, 모델 변경 해야함.
            --by Hyunmin */


        // 사진 개수 검증 - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        if (dto.getPics() != null) {
            CommonUtils.checkSizeIfOverLimitNumThrow(IllegalProductPicsException.class, ILLEGAL_PRODUCT_PICS_EX_MESSAGE,
                    dto.getPics().stream(), 9);
        }

        // iuser 있는지 체크
        CommonUtils.ifFalseThrow(NoSuchUserException.class, NO_SUCH_USER_EX_MESSAGE, productRepository.findIuserCountBy(dto.getIuser()));

        // 카테고리 검증 - 예외 코드, 메시지 를 위해 직접 검증 (!@Validated)
        CommonUtils.ifCategoryNotContainsThrowOrReturn(dto.getCategory());

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


        // 주소 검증 (ieupmyun)
        Integer addrBy = checkAddrInDb(dto.getAddr());


        // logic
        Map<String, Double> axis = CommonUtils.getAxis(dto.getAddr().concat(dto.getRestAddr()));
        // insert 할 객체 준비 완.
        InsProdBasicInfoDto insProdBasicInfoDto = new InsProdBasicInfoDto(dto, addrBy, axis.get(AXIS_X), axis.get(AXIS_Y));
        insProdBasicInfoDto.setMainPicObj(CommonUtils.savePic(dto.getMainPic()));
        if (productRepository.saveProduct(insProdBasicInfoDto) == 1 && dto.getPics() != null) {
            // pics 에 insert 할 객체
            InsProdPicsDto insProdPicsDto = new InsProdPicsDto(insProdBasicInfoDto.getIproduct(), CommonUtils.savePic(dto.getPics()));
            productRepository.savePics(insProdPicsDto);
        }

        return new ResVo(1);
    }


    /**
     * 제품 정보 수정 <br>
     *
     * @param dto
     * @return ResVo
     */
    @Transactional
    public ResVo putProduct(ProductUpdDto dto) {

        // 삭제사진 필요시 삭제
        if (!dto.getDelPics().isEmpty()) {
            if (productRepository.deletePics(dto.getIproduct(), dto.getDelPics()) == 0) {
                throw new RuntimeException(SERVER_ERR_MESSAGE);
            }
        }
        // 병합하지 않아도 되는 데이터 검증

        // 주소 검증
        if (dto.getAddr() != null) {
            dto.setIaddr(checkAddrInDb(dto.getAddr()));
        }
        UpdProdBasicDto fromDb = productRepository.findProductByForUpdate(new GetProductBaseDto(CommonUtils.ifCategoryNotContainsThrowOrReturn(dto.getCategory()), dto.getIproduct()));
        // 병합
        Integer price = dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice();
        UpdProdBasicDto mergedData = new UpdProdBasicDto(
                dto.getPrice() == null ? fromDb.getPrice() : dto.getPrice(),
                dto.getBuyDate() == null ? fromDb.getBuyDate() : dto.getBuyDate(),
                dto.getRentalStartDate() == null ? fromDb.getRentalStartDate() : dto.getRentalStartDate(),
                dto.getRentalEndDate() == null ? fromDb.getRentalEndDate() : dto.getRentalEndDate()
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
        if (!dto.getPics().isEmpty()) {
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


        // 문제 없음.


        // 문제 없으면 추가 사진 insert
        if (!dto.getPics().isEmpty()) {
            productRepository.savePics(new InsProdPicsDto(dto.getIproduct(), CommonUtils.savePic(dto.getPics())));
        }


        // update 할 객체 세팅
        dto.setStoredMainPic(dto.getMainPic() == null ? null : CommonUtils.savePic(dto.getMainPic()));
        dto.setDeposit(dto.getDepositPer() == null ? null : CommonUtils.getDepositFromPer(price, dto.getDeposit()));
        dto.setIcategory(CommonUtils.ifCategoryNotContainsThrowOrReturn(dto.getCategory()));
        if (dto.getAddr() != null && dto.getRestAddr() != null) {
            Map<String, Double> axis = CommonUtils.getAxis(dto.getAddr().concat(dto.getRestAddr()));
            dto.setX(axis.get(AXIS_X));
            dto.setY(axis.get(AXIS_Y));
        }

        // do update
        return new ResVo(productRepository.updateProduct(dto));
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
     * @param iuser
     * @param div
     * @return ResVo
     */
    public ResVo delProduct(Integer iproduct, Integer iuser, Integer div) {

        DelProductBaseDto delProductBaseDto = new DelProductBaseDto(iproduct, iuser, div * -1);
        if (productRepository.updateProductStatus(delProductBaseDto) == 0) {
            throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        }

        return new ResVo(1);
    }

    public List<ProductUserVo> getUserProductList(Integer iuser, Integer page) {
        List<GetProductListResultDto> productListBy = productRepository.findProductListBy(new GetProductListDto(iuser));
        CommonUtils.checkNullOrZeroIfCollection(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE, productListBy);

        List<ProductUserVo> result = new ArrayList<>();
        productListBy.forEach(product -> {
            ProductUserVo productListVo = new ProductUserVo(product);

            productListVo.setUserPic(
                    CommonUtils.getPic(new StoredFileInfo(product.getUserRequestPic(), product.getUserStoredPic()))
            );
            productListVo.setProdPic(
                    CommonUtils.getPic(new StoredFileInfo(product.getProdMainRequestPic(), product.getProdMainStoredPic()))
            );
            result.add(productListVo);
        });

        return result;

    }









    /*
        ------- Extracted Method -------
     */



    private Integer checkAddrInDb(String addr) {
        List<Integer> addrBy = productRepository.findAddrBy(CommonUtils.subEupmyun(addr));
        if (addrBy.isEmpty()) throw new BadAddressInfoException(BAD_ADDRESS_INFO_EX_MESSAGE);
        CommonUtils.checkSizeIfOverLimitNumThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
                addrBy.stream(), 1);
        return addrBy.get(0);
    }



}
