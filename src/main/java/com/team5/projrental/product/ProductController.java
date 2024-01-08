package com.team5.projrental.product;

import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.proc.GetProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/prod")
public class ProductController {

    private final ProductService productService;

    @GetMapping("{category}")
    public List<ProductListVo> getProductList(@RequestParam(required = false) Integer sort,
                                              @RequestParam(required = false) String search,
                                              @PathVariable String category) {

        return productService.getProductList(new GetProductDto(sort, search, category));
    }


}
