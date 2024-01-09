package com.team5.projrental.product;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.BadAddressInfoException;
import com.team5.projrental.product.model.proc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductMapper productMapper;

    //
    public String countView(Integer iproduct) {
        if (productMapper.countView(iproduct) == 1) {
            return "SUCCEED";
        }
        return "FALSE";
    }
    //

    public List<GetProductListResultDto> findProductListBy(GetProductDto getProductDto) {
        return productMapper.getProductList(getProductDto);
    }

    public GetProductResultDto findProductBy(Integer iproduct) {
        return productMapper.getProduct(iproduct);
    }

    public List<GetProdEctPicDto> findPicsBy(Integer productPK) {
        return productMapper.getProdEctPics(productPK);
    }

    public int saveProduct(InsProdBasicInfoDto insProdBasicInfoDto) {
        return productMapper.insProduct(insProdBasicInfoDto);
    }

    public void savePics(InsProdPicsDto insProdPicsDto) {
        productMapper.insPics(insProdPicsDto);
    }

    public List<Integer> findAddrBy(List<String> eupmyun) {
        return productMapper.getIEupmyun(eupmyun);
    }
}