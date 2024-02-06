package com.team5.projrental.product;

import com.team5.projrental.common.aop.model.DelCacheWhenCancel;
import com.team5.projrental.product.model.CanNotRentalDateVo;
import com.team5.projrental.product.model.Categories;
import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.product.model.proc.*;
import com.team5.projrental.product.model.review.ReviewGetDto;
import com.team5.projrental.product.model.review.ReviewResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements RefProductRepository {

    private final ProductMapper productMapper;

    //
    @Override
    public Integer findRentalPriceBy(Integer iproduct) {
        return productMapper.getRentalPricePerDay(iproduct);
    }
    //

    //
    @Override
    public boolean findIproductCountBy(Integer iproduct) {
        return productMapper.checkIproduct(iproduct) == 1;
    }
    //

    //
    @Override
    public boolean findIuserCountBy(Integer iuser) {
        return productMapper.checkIuser(iuser) == 1;
    }
    //

    //
    @Override
    public String countView(GetProductViewAopDto getProductViewAopDto) {
        if (productMapper.countView(getProductViewAopDto) == 1) {
            return "SUCCEED";
        }
        return "FALSE";
    }
    //
    @Override
    public List<GetProductListResultDto> findProductListBy(GetProductListDto getProductListDto) {
        return productMapper.getProductList(getProductListDto);
    }

    @Override
    public List<GetProductListResultDto> findProductListForMain(Categories categories, int page, int prodPerPage) {
        return productMapper.findProductListForMain(categories, page, prodPerPage);
    }

    @Override
    public GetProductResultDto findProductBy(GetProductBaseDto getProductBaseDto) {
        return productMapper.getProduct(getProductBaseDto);
    }
    @Override
    public List<PicsInfoVo> findPicsBy(Integer productPK) {
        return productMapper.getProdEctPics(productPK);
    }
    @Override
    public int saveProduct(InsProdBasicInfoDto insProdBasicInfoDto) {
        return productMapper.insProduct(insProdBasicInfoDto);
    }
    @Override
    public int savePics(InsProdPicsDto insProdPicsDto) {
        return productMapper.insPics(insProdPicsDto);
    }

//    public List<Integer> findAddrBy(List<String> eupmyun) {
//        return productMapper.getIEupmyun(eupmyun);
//    }
@Override
    public Integer deletePics(Integer iproduct, List<Integer> delPic) {
        return productMapper.deletePic(iproduct, delPic);
    }
    @Override
    public UpdProdBasicDto findProductByForUpdate(GetProductBaseDto getProductBaseDto) {
        return productMapper.getProductForUpdate(getProductBaseDto);

    }
    @Override
    public int updateProduct(ProductUpdDto productUpdDto) {
        return productMapper.updateProduct(productUpdDto);
    }
    @Override
    public int findPicsCount(Integer iproduct) {
        return productMapper.getPicCount(iproduct);
    }
    @Override
    public int updateProductStatus(DelProductBaseDto delProductBaseDto) {
        return productMapper.changeProdStatus(delProductBaseDto);
    }
    @Override
    public List<ReviewResultVo> getReview(ReviewGetDto dto) {
        return productMapper.getReview(dto);
    }

    @Override
    public List<CanNotRentalDateVo> getLendDatesBy(Integer iproduct) {
        return productMapper.getLendStartDateAndEndDate(iproduct);
    }

    @Override
    public List<String> getPicsAllBy(List<Integer> ipics) {
        return productMapper.getPicsAllBy(ipics);
    }

    @Override
    public List<CanNotRentalDateVo> findDisabledDatesBy(CanNotRentalDateDto dto) {
        return productMapper.findDisabledDatesBy(dto);
    }


    @Override
    public Integer findStockCountBy(int iproduct) {
        return productMapper.findStockCountBy(iproduct);
    }

    @Override
    public String findMainPicPathForDelBy(int iproduct) {
        return productMapper.findMainPicPathForDelBy(iproduct);
    }

    @Override
    public List<String> findSubPicsPathForDelBy(int iproduct) {
        return productMapper.findSubPicsPathForDelBy(iproduct);
    }


    @Override
    public List<Integer> getAllIproductsLimit(int limit) {
        return productMapper.getAllIproductsLimit(limit);
    }

    @Override
    public List<DelCacheWhenCancel> checkStatusBothAndGetIproduct(int ipayment) {
        return productMapper.checkStatusBothAndGetIproduct(ipayment);
    }
}
