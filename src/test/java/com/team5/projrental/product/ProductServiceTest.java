package com.team5.projrental.product;

import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.security.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    AxisGenerator axisGenerator;
    @MockBean
    AuthenticationFacade authenticationFacade;

    @Autowired
    ProductService productService;

    @Test
    void axisGeneratorTest() {

    }

    @Test
    void getProductList() {




    }

    @Test
    void getProduct() {
    }

    @Test
    void postProduct() {
    }

    @Test
    void putProduct() {
    }

    @Test
    void delProduct() {
    }

    @Test
    void getUserProductList() {
    }
}