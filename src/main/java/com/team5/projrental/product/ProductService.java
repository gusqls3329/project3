package com.team5.projrental.product;

import com.team5.projrental.product.model.CurProductListVo;
import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.proc.GetProdEctPicDto;
import com.team5.projrental.product.model.proc.GetProductDto;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductListVo> getProductList(GetProductDto getProductDto) {

        List<GetProductListResultDto> products = productRepository.findProductListBy(getProductDto);

        List<ProductListVo> result = new ArrayList<>();
        products.forEach(product -> {
            ProductListVo productListVo = new ProductListVo(product);

            productListVo.setUserPic(
                    getPic(new StoredFileInfo(product.getUserRequestPic(), product.getUserStoredPic()))
            );
            productListVo.setProdPic(
                    getPic(new StoredFileInfo(product.getProdMainRequestPic(), product.getProdMainStoredPic()))
            );
            result.add(productListVo);
        });

        return result;
    }

    public CurProductListVo getProduct(Integer iproduct) {
        // 사진을 제외한 모든 정보 획득
        GetProductResultDto productBy = productRepository.findProductBy(iproduct);

        Integer productPK = productBy.getIproduct();
        List<GetProdEctPicDto> ectPics = productRepository.findPicsBy(productPK);
        List<PicSet> resultEctPic = new ArrayList<>();
        ectPics.forEach(p -> {
            resultEctPic.add(new PicSet(getPic(new StoredFileInfo(p.getProdRequestPic(), p.getProdStoredPic())), p.getIpics()));
        });
        CurProductListVo result = new CurProductListVo(productBy);
        result.setProdPics(resultEctPic);
        return result;
    }

    /*
    ------- ext Method -------
     */

    // 파일 업로드 배운 후 완성시킬 예정.
    private Resource getPic(StoredFileInfo pic) {
        return null;
    }

    // 파일 업로드 배운 후 완성시킬 예정.
    private List<Resource> getPic(List<StoredFileInfo> pic) {
        List<Resource> results = new ArrayList<>();
        pic.forEach(p -> results.add(getPic(p)));
        return results;
    }

}
