package com.team5.projrental.product;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.*;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({ProductController.class})
class ProductControllerTest {


    @MockBean
    ProductService productService;

    @Autowired
    ProductController productController;

    @Test
    void getProductList() {

        when(productService.getProductList(null, null, 1, 1)).thenReturn(List.of(new ProductListVo(new GetProductListResultDto())));
        productController.getProductList(null, null, 1, 1);

    }

    @Test
    void getProduct() {

        productController.getProduct(1, 1);

    }

    @Test
    void postProduct() {
//        when(productService.postProduct(any())).thenReturn(new ResVo(1));
//        assertThat(productController.postProduct(new ProductInsDto()).getResult()).isEqualTo(1);

    }

    /*@Test
    void putProduct() {

        when(productService.putProduct(any())).thenReturn(new ResVo(1));

        assertThat(productController.putProduct(new ProductUpdDto()).getResult()).isEqualTo(1);

    }*/

    @Test
    void delProduct() {

        when(productService.delProduct(any(), any())).thenReturn(new ResVo(1));
        assertThat(productController.delProduct(1, 1).getResult()).isEqualTo(1);

    }

}