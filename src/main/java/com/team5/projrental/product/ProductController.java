package com.team5.projrental.product;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ErrorResultVo;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/prod")
public class ProductController {

    private final ProductService productService;

    @Validated
    @GetMapping("{icategory}")
    public List<ProductListVo> getProductList(@RequestParam(required = false) Integer sort,
                                              @RequestParam(required = false) String search,
                                              @RequestParam @Min(1) int page,
                                              @PathVariable @Min(1) int icategory) {

        return productService.getProductList(sort, search, icategory, (page - 1) * Const.PROD_PER_PAGE);
    }

    @Validated
    @GetMapping("/api/prod/{icategory}/{iproduct}")
    public ProductVo getProduct(@PathVariable @Min(1) Integer icategory, @PathVariable @Min(1) Integer iproduct) {
        return productService.getProduct(icategory, iproduct);
    }

    /*
    pics + mainPic 개수 검증 - 10개 이하 -> iuser 가 존재하는지 검증 -> category 존재여부 검증 ->
    price 양수 검증 -> buyDate 오늘보다 이전인지 검증 -> depositPer 70 이상 100 이하 검증 ->
    오늘이 rentalStartDate 보다 이전이 아닌지 검증 -> rentalEndDate 가 rentalStartDate 보다 이전이 아닌지 검증 ->
    depositPer 를 price 기준 퍼센트 금액으로 환산 ->  본 로직
    -> addr + restAddr 기준으로 x, y 좌표 획득 -> insert model 객체 생성 -> insert
     */
    @PostMapping
    public ResVo postProduct(@Validated ProductInsDto dto) {
        return productService.postProduct(dto);
    }

    @PutMapping
    public ResVo putProduct(@Validated ProductUpdDto dto) {
        return productService.putProduct(dto);
    }

    @Validated
    @DeleteMapping("/{iproduct}")
    public ResVo delProduct(@PathVariable @Min(1) Integer iproduct,
                            @RequestParam @Range(min = 1, max = 1) Integer div) {
        return productService.delProduct(iproduct, div);
    }

    @Validated
    @GetMapping("/list")
    public List<ProductUserVo> getUserProductList(@RequestParam @Min(1) @NotNull Integer page) {
        return productService.getUserProductList((page - 1) * Const.PROD_PER_PAGE);
    }
}
