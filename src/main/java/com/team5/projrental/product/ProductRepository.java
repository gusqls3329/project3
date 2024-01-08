package com.team5.projrental.product;

import com.team5.projrental.product.model.proc.GetProductDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductMapper productMapper;

    public List<GetProductResultDto> findProductBy(GetProductDto getProductDto) {
        return productMapper.getProduct(getProductDto);
    }

}
