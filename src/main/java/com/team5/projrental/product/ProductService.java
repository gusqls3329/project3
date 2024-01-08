package com.team5.projrental.product;

import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.proc.GetProductDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductListVo> getProductList(GetProductDto getProductDto) {

        List<GetProductResultDto> products = productRepository.findProductBy(getProductDto);

        List<ProductListVo> result = new ArrayList<>();
        products.forEach(product -> {
            ProductListVo productListVo = new ProductListVo(product);
            productListVo.setUserPic(getPic(product.getUserPic()));
            productListVo.setProdPic(getPic(product.getProdPic()));
            result.add(productListVo);
        });

        return result;
    }

    // 파일 업로드 배운 후 완성시킬 예정.
    private ResponseEntity<Resource> getPic(String pic) {
        return null;
    }

    // 파일 업로드 배운 후 완성시킬 예정.
    private List<ResponseEntity<Resource>> getPic(String... pic) {
        List<ResponseEntity<Resource>> results = new ArrayList<>();
        for (String p : pic) {
            results.add(getPic(p));
        }
        return results;
    }

}
