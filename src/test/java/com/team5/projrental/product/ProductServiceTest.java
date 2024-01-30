package com.team5.projrental.product;

import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.exception.NoSuchProductException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.common.security.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    KakaoAxisGenerator axisGenerator;
    @MockBean
    AuthenticationFacade authenticationFacade;

    @MockBean
    MyFileUtils myFileUtils;

    @Autowired
    ProductService productService;


/*    @Test
    void getProductList() {


        GetProductListResultDto myMockResult = new GetProductListResultDto();
        myMockResult.setIuser(1);
        when(productRepository.findProductListBy(any())).thenReturn(List.of(myMockResult,
                new GetProductListResultDto()));

        List<ProductListVo> productList = productService.getProductList(null, null, 1, 0);
        assertThat(productList.get(0).getIuser()).isEqualTo(1);
        assertThatThrownBy(() -> productService.getProductList(5, null, 1, 1))
                .isInstanceOf(BadInformationException.class);
        when(productRepository.findProductListBy(any())).thenReturn(null);
        assertThatThrownBy(() -> productService.getProductList(null, null, 1, 0))
                .isInstanceOf(NoSuchProductException.class);
        when(productRepository.findProductListBy(any())).thenReturn(new ArrayList<>());
        assertThatThrownBy(() -> productService.getProductList(null, null, 1, 0))
                .isInstanceOf(NoSuchProductException.class);
    }*/


    @Test
    void delProduct() {

        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        when(productRepository.updateProductStatus(any())).thenReturn(1);


        ResVo resVo = productService.delProduct(1, 1);
        assertThat(resVo.getResult()).isEqualTo(1);

        when(productRepository.updateProductStatus(any())).thenReturn(0);
        assertThatThrownBy(() -> productService.delProduct(1, 1))
                .isInstanceOf(BadInformationException.class);


    }

}