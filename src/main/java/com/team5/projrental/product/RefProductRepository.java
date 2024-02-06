package com.team5.projrental.product;


import com.team5.projrental.common.aop.model.DelCacheWhenCancel;
import com.team5.projrental.product.model.CanNotRentalDateVo;
import com.team5.projrental.product.model.Categories;
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

    List<GetProductListResultDto> findProductListForMain(Categories categories, int page, int prodPerPage);

    GetProductResultDto findProductBy(GetProductBaseDto getProductBaseDto);

    List<PicsInfoVo> findPicsBy(Integer productPK);

    int saveProduct(InsProdBasicInfoDto insProdBasicInfoDto);

    int savePics(InsProdPicsDto insProdPicsDto);



    Integer deletePics(Integer iproduct, List<Integer> delPic);

    UpdProdBasicDto findProductByForUpdate(GetProductBaseDto getProductBaseDto);


    int updateProduct(ProductUpdDto productUpdDto);

    int findPicsCount(Integer iproduct);

    int updateProductStatus(DelProductBaseDto delProductBaseDto);

    List<ReviewResultVo> getReview(ReviewGetDto dto);

    List<CanNotRentalDateVo> getLendDatesBy(Integer iproduct);

    List<String> getPicsAllBy(List<Integer> ipics);

    List<CanNotRentalDateVo> findDisabledDatesBy(CanNotRentalDateDto dto);

    Integer findStockCountBy(int iproduct);

    String findMainPicPathForDelBy(int iproduct);

    List<String> findSubPicsPathForDelBy(int iproduct);

    List<Integer> getAllIproductsLimit(int limit);

    List<DelCacheWhenCancel> checkStatusBothAndGetIproduct(int ipayment);
}
