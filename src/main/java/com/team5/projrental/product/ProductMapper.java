package com.team5.projrental.product;

import com.team5.projrental.product.model.proc.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //

    //

    List<GetProductListResultDto> getProductList(GetProductDto getProductDto);

    GetProductResultDto getProduct(Integer iproduct);

    List<GetProdEctPicDto> getProdEctPics(Integer productPK);

    int countView(Integer iproduct);

    int insProduct(InsProdBasicInfoDto insProdBasicInfoDto);

    void insPics(InsProdPicsDto insProdPicsDto);

    List<Integer> getIEupmyun(List<String> eupmyun);

}
