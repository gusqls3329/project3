package com.team5.projrental.product;

import com.team5.projrental.product.model.proc.GetProductDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<GetProductResultDto> getProduct(GetProductDto getProductDto);
}
