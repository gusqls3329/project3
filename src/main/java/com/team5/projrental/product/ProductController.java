package com.team5.projrental.product;

import com.team5.projrental.product.model.ProductListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/prod")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductListVo getProductList(@RequestParam(required = false) Integer sort,
                                        @RequestParam(required = false) String search) {
        return null;
    }


}
