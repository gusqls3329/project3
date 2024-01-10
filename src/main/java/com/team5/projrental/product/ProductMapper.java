package com.team5.projrental.product;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.product.model.proc.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    //
    int checkIuser(Integer iuser);
    //


    //
    int countView(Integer iproduct);
    //

    List<GetProductListResultDto> getProductList(GetProductListDto getProductListDto);

    GetProductResultDto getProduct(GetProductBaseDto getProductBaseDto);

    List<GetProdEctPicDto> getProdEctPics(Integer productPK);



    int insProduct(InsProdBasicInfoDto insProdBasicInfoDto);

    void insPics(InsProdPicsDto insProdPicsDto);

    List<Integer> getIEupmyun(List<String> eupmyun);

    Integer deletePic(@Param("iproduct") Integer iproduct, @Param("delPics") List<Integer> delPic);

    UpdProdBasicDto getProductForUpdate(GetProductBaseDto getProductBaseDto);

    int updateProduct(ProductUpdDto productUpdDto);

    int getPicCount(Integer iproduct);
}
