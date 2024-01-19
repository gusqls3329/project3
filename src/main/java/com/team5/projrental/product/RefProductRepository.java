package com.team5.projrental.product;


import com.team5.projrental.product.model.CanNotRentalDate;
import com.team5.projrental.product.model.ProductToggleFavDto;
import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.product.model.proc.*;
import com.team5.projrental.product.model.review.ReviewGetDto;
import com.team5.projrental.product.model.review.ReviewResultVo;

import java.util.List;

public interface RefProductRepository {

    //
    Integer findRentalPriceBy(Integer iproduct);
    //

    //
    boolean findIproductCountBy(Integer iproduct);
    //

    //
    boolean findIuserCountBy(Integer iuser);
    //

    //
    String countView(GetProductViewAopDto getProductViewAopDto);
    //

    List<GetProductListResultDto> findProductListBy(GetProductListDto getProductListDto);

    GetProductResultDto findProductBy(GetProductBaseDto getProductBaseDto);

    List<GetProdEctPicDto> findPicsBy(Integer productPK);

    int saveProduct(InsProdBasicInfoDto insProdBasicInfoDto);

    int savePics(InsProdPicsDto insProdPicsDto);



    Integer deletePics(Integer iproduct, List<Integer> delPic);

    UpdProdBasicDto findProductByForUpdate(GetProductBaseDto getProductBaseDto);


    int updateProduct(ProductUpdDto productUpdDto);

    int findPicsCount(Integer iproduct);

    int updateProductStatus(DelProductBaseDto delProductBaseDto);

    List<ReviewResultVo> getReview(ReviewGetDto dto);

    List<CanNotRentalDate> getLendDatesBy(Integer iproduct);

}
