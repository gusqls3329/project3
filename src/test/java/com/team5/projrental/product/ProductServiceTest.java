package com.team5.projrental.product;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.BadInformationException;
import com.team5.projrental.common.exception.NoSuchProductException;
import com.team5.projrental.common.exception.checked.NotContainsDotException;
import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.product.model.ProductInsDto;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.common.security.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    AxisGenerator axisGenerator;
    @MockBean
    AuthenticationFacade authenticationFacade;

    @MockBean
    MyFileUtils myFileUtils;

    @Autowired
    ProductService productService;

    @Test
    void axisGeneratorTest() {

    }

    @Test
    void getProductList() {


        GetProductListResultDto myMockResult = new GetProductListResultDto();
        myMockResult.setIuser(1);
        when(productRepository.findProductListBy(any())).thenReturn(List.of(myMockResult,
                new GetProductListResultDto()));
        when(myFileUtils.getPic(any(StoredFileInfo.class))).thenReturn(null);

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
    }


    @Test
    void postProduct() throws IOException {

        // 문제 없는 데이터 생성

        MockMultipartFile mockMultipartFile1 = new MockMultipartFile("test", "test", "testm",
                new FileInputStream("test"));
        MockMultipartFile mockMultipartFile2 = new MockMultipartFile("test", "test", "testm",
                new FileInputStream("test"));
        ProductInsDto productInsDto = new ProductInsDto();
        productInsDto.setIcategory(1);
        productInsDto.setBuyDate(LocalDate.of(2000, 1, 1));
        productInsDto.setRentalStartDate(LocalDate.of(2025, 1, 1));
        productInsDto.setRentalEndDate(LocalDate.of(2026, 1, 1));
        productInsDto.setPics(List.of(mockMultipartFile1, mockMultipartFile2));

        try {
            when(myFileUtils.savePic(List.of(mockMultipartFile1, mockMultipartFile2), Const.CATEGORY_PRODUCT_SUB)).thenReturn(null);
        } catch (NotContainsDotException ignored) {
        }
        when(productRepository.savePics(any())).thenReturn(1);
        when(productRepository.findAddrBy(any())).thenReturn(List.of(13));
        when(axisGenerator.getAxis(any())).thenReturn(Map.of("test", 1.1, "test2", 2.2));
        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        when(productRepository.findIuserCountBy(any())).thenReturn(true);
        when(productRepository.saveProduct(any())).thenReturn(1);
//        productService.postProduct();

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