package com.team5.projrental.product;

import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.product.model.proc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductMapper productMapper;

    //
    public int findRentalPriceBy(Integer iproduct) {
        return productMapper.getRentalPricePerDay(iproduct);
    }
    //

    //
    public boolean findIproductCountBy(Integer iproduct) {
        return productMapper.checkIproduct(iproduct) == 1;
    }
    //

    //
    public boolean findIuserCountBy(Integer iuser) {
        return productMapper.checkIuser(iuser) == 1;
    }
    //

    //
    public String countView(GetProductViewAopDto getProductViewAopDto) {
        if (productMapper.countView(getProductViewAopDto) == 1) {
            return "SUCCEED";
        }
        return "FALSE";
    }
    //

    public List<GetProductListResultDto> findProductListBy(GetProductListDto getProductListDto) {
        return productMapper.getProductList(getProductListDto);
    }

    public GetProductResultDto findProductBy(GetProductBaseDto getProductBaseDto) {
        return productMapper.getProduct(getProductBaseDto);
    }

    public List<GetProdEctPicDto> findPicsBy(Integer productPK) {
        return productMapper.getProdEctPics(productPK);
    }

    public int saveProduct(InsProdBasicInfoDto insProdBasicInfoDto) {
        return productMapper.insProduct(insProdBasicInfoDto);
    }

    public int savePics(InsProdPicsDto insProdPicsDto) {
        return productMapper.insPics(insProdPicsDto);
    }

    public List<Integer> findAddrBy(List<String> eupmyun) {
        return productMapper.getIEupmyun(eupmyun);
    }

    public Integer deletePics(Integer iproduct, List<Integer> delPic) {
        return productMapper.deletePic(iproduct, delPic);
    }

    public UpdProdBasicDto findProductByForUpdate(GetProductBaseDto getProductBaseDto) {
        return productMapper.getProductForUpdate(getProductBaseDto);

    }

    public int updateProduct(ProductUpdDto productUpdDto) {
        return productMapper.updateProduct(productUpdDto);
    }

    public int findPicsCount(Integer iproduct) {
        return productMapper.getPicCount(iproduct);
    }

    public int updateProductStatus(DelProductBaseDto delProductBaseDto) {
        return productMapper.changeProdStatus(delProductBaseDto);
    }


}
