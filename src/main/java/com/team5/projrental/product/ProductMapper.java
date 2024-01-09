package com.team5.projrental.product;

import com.team5.projrental.product.model.proc.GetProdEctPicDto;
import com.team5.projrental.product.model.proc.GetProductDto;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<GetProductListResultDto> getProductList(GetProductDto getProductDto);

    GetProductResultDto getProduct(Integer iproduct);

    List<GetProdEctPicDto> getProdEctPics(Integer productPK);
}
