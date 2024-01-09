package com.team5.projrental.product;

import com.team5.projrental.common.exception.BadDateInfoException;
import com.team5.projrental.common.exception.NotEnoughProductPics;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.product.model.CurProductListVo;
import com.team5.projrental.product.model.ProductInsDto;
import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.proc.GetProdEctPicDto;
import com.team5.projrental.product.model.proc.GetProductDto;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.team5.projrental.common.Const.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CommonUtils commonUtils;

    public List<ProductListVo> getProductList(GetProductDto getProductDto) {

        List<GetProductListResultDto> products = productRepository.findProductListBy(getProductDto);

        List<ProductListVo> result = new ArrayList<>();
        products.forEach(product -> {
            ProductListVo productListVo = new ProductListVo(product);

            productListVo.setUserPic(
                    getPic(new StoredFileInfo(product.getUserRequestPic(), product.getUserStoredPic()))
            );
            productListVo.setProdPic(
                    getPic(new StoredFileInfo(product.getProdMainRequestPic(), product.getProdMainStoredPic()))
            );
            result.add(productListVo);
        });

        return result;
    }

    public CurProductListVo getProduct(Integer iproduct) {
        // 사진을 제외한 모든 정보 획득
        GetProductResultDto productBy = productRepository.findProductBy(iproduct);

        Integer productPK = productBy.getIproduct();
        List<GetProdEctPicDto> ectPics = productRepository.findPicsBy(productPK);
        List<PicSet> resultEctPic = new ArrayList<>();
        ectPics.forEach(p -> {
            resultEctPic.add(new PicSet(getPic(new StoredFileInfo(p.getProdRequestPic(), p.getProdStoredPic())), p.getIpics()));
        });
        CurProductListVo result = new CurProductListVo(productBy);
        result.setProdPics(resultEctPic);
        return result;
    }


    public ResVo postProduct(ProductInsDto dto) {
    /*
     pics + mainPic 개수 검증 - 10개 이하 -> iuser 가 존재하는지 검증 -> category 존재여부 검증 ->
     price 양수 검증(@V) -> buyDate 오늘보다 이전인지 검증 -> depositPer 70 이상 100 이하 검증(@V) ->
     오늘이 rentalStartDate 보다 이전이 아닌지 검증 -> rentalEndDate 가 rentalStartDate 보다 이전이 아닌지 검증
     */
    /* TODO: 1/9/24
        여기부터 작업 다시 시작.
        =
        문제 없다면 본 로직 시작
        depositPer 를 price 기준 퍼센트 금액으로 환산 ->
        -> addr + restAddr 기준으로 x, y 좌표 획득 -> insert model 객체 생성 -> insert
        --by Hyunmin */

        if (dto.getPics() != null) {
            commonUtils.checkSizeIfOverLimitNumThrow(NotEnoughProductPics.class, NOT_ENOUGH_PRODUCT_PICS_EX_MESSAGE,
                    dto.getPics().stream(), 9);
        }
        /* TODO: 1/9/24
            유저 검증 체크 해야함 - userSel
            --by Hyunmin */
        commonUtils.ifCategoryNotContainsThrow(dto.getCategory());
        commonUtils.ifAfterThrow(
                BadDateInfoException.class, BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE,
                LocalDate.now(), dto.getBuyDate()
        );
        commonUtils.ifBeforeThrow(
                BadDateInfoException.class, RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE,
                dto.getRentalStartDate(), LocalDate.now()
        );
        commonUtils.ifBeforeThrow(BadDateInfoException.class, RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE
                , dto.getRentalEndDate(), dto.getRentalStartDate());


        return null;
    }

    /*
    ------- ext Method -------
     */

    // 파일 업로드 배운 후 완성시킬 예정.
    private Resource getPic(StoredFileInfo pic) {
        return null;
    }

    // 파일 업로드 배운 후 완성시킬 예정.
    private List<Resource> getPic(List<StoredFileInfo> pic) {
        List<Resource> results = new ArrayList<>();
        pic.forEach(p -> results.add(getPic(p)));
        return results;
    }


}
