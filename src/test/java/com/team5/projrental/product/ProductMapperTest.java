package com.team5.projrental.product;

import com.team5.projrental.product.model.ProductInsDto;
import com.team5.projrental.product.model.proc.GetProductBaseDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import com.team5.projrental.product.model.proc.InsProdBasicInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    void getRentalPricePerDay() {
    }

    @Test
    void checkIuser() {
    }

    @Test
    void countView() {
    }

    @Test
    void getProductList() {
    }

    @Test
    void getProduct() {

        GetProductResultDto product = productMapper.getProduct(new GetProductBaseDto(1, 1));
        log.info("product = {}", product);

    }

    @Test
    void getProdEctPics() {
    }

    @Test
    void insProduct() {

        int result = productMapper.insProduct(new InsProdBasicInfoDto(new ProductInsDto(1, "test", "test", "test", "test",
                new MockMultipartFile("test", new byte[1]), List.of(new MockMultipartFile("test", new byte[1])), 100,
                10, 80, LocalDate.of(2022, 11, 2), LocalDate.of(2024, 2, 2), LocalDate.of(2024, 4, 2), "mobile"),
                34, 1.1, 2.2));
        Assertions.assertThat(result).isEqualTo(1);


    }

    @Test
    void insPics() {
    }

    @Test
    void getIEupmyun() {
    }

    @Test
    void deletePic() {
    }

    @Test
    void getProductForUpdate() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getPicCount() {
    }

    @Test
    void changeProdStatus() {
    }

    @Test
    void updateIpayment() {
    }

    @Test
    void checkIproduct() {
    }
}