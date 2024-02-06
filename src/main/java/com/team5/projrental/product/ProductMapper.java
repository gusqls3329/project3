package com.team5.projrental.product;

import com.team5.projrental.common.aop.model.DelCacheWhenCancel;
import com.team5.projrental.product.model.CanNotRentalDateVo;
import com.team5.projrental.product.model.Categories;
import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.product.model.proc.*;
import com.team5.projrental.product.model.review.ReviewGetDto;
import com.team5.projrental.product.model.review.ReviewResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // for test
    int getIStatus(Integer iproduct);
    //

    //    for test
    int getViewForTest(Integer iproduct);
    //

    //    for test
    int getPicsCountForTest(Integer iproduct);
    //


    //    for test
    List<Integer> getAllIpics(Integer iproduct);
    //

    //
    Integer getRentalPricePerDay(Integer iproduct);
    //

    //
    int checkIuser(Integer iuser);
    //


    //
    int countView(GetProductViewAopDto getProductViewAopDto);
    //

    List<GetProductListResultDto> getProductList(GetProductListDto getProductListDto);

    List<GetProductListResultDto> findProductListForMain(Categories categories, int page, int prodPerPage);

    GetProductResultDto getProduct(GetProductBaseDto getProductBaseDto);

    List<PicsInfoVo> getProdEctPics(Integer productPK);


    int insProduct(InsProdBasicInfoDto insProdBasicInfoDto);

    int insPics(InsProdPicsDto insProdPicsDto);

//    List<Integer> getIEupmyun(List<String> eupmyun);

    Integer deletePic(Integer iproduct, List<Integer> delPics);

    UpdProdBasicDto getProductForUpdate(GetProductBaseDto getProductBaseDto);

    int updateProduct(ProductUpdDto productUpdDto);

    int getPicCount(Integer iproduct);

    int changeProdStatus(DelProductBaseDto delProductBaseDto);

    int checkIproduct(Integer iproduct);

    List<ReviewResultVo> getReview(ReviewGetDto dto);

    List<CanNotRentalDateVo> getLendStartDateAndEndDate(Integer iproduct);

    List<String> getPicsAllBy(List<Integer> ipics);

    List<CanNotRentalDateVo> findDisabledDatesBy(CanNotRentalDateDto dto);

    Integer findStockCountBy(int iproduct);

    String findMainPicPathForDelBy(int iproduct);

    List<String> findSubPicsPathForDelBy(int iproduct);


    // cache 세팅용
    List<Integer> getAllIproductsLimit(int limit);

    List<DelCacheWhenCancel> checkStatusBothAndGetIproduct(int ipayment);
}
