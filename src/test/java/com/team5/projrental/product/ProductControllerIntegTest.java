package com.team5.projrental.product;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.model.ErrorResultVo;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.ProductUserVo;
import com.team5.projrental.product.model.ProductVo;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ProductControllerIntegTest {

    String token;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    ProductController controller;

    MultipartFile multipartFile;

    @BeforeEach
    public void before() throws Exception {
        SigninDto dto = new SigninDto();
        dto.setUid("qwqwqw11");
        dto.setUpw("12121212");

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = om.readValue(contentAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();

        String fileName = "pic.jpg";
        String filePath = "D:/ee/" + fileName;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        this.multipartFile = new MockMultipartFile("pic", fileName, "png", fileInputStream);


    }



    @Test
    void getProduct() throws Exception {
        String responseResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/prod/3/11")
                        .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();

        ProductVo productVo = om.readValue(responseResult, ProductVo.class);
        assertThat(productVo.getDeposit()).isEqualTo(1200000);
        assertThat(productVo.getIsLiked()).isEqualTo(0);

    }




    //
    @Test
    void delProduct() throws Exception {

        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/prod/11?div=1")
                .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();

        ResVo resVo = om.readValue(result, ResVo.class);
        assertThat(resVo.getResult()).isEqualTo(1);

        String result2 = mockMvc.perform(MockMvcRequestBuilders.delete("/api/prod/10?div=1")
                .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();

        ErrorResultVo errorResultVo = om.readValue(result2, ErrorResultVo.class);
        org.junit.jupiter.api.Assertions.assertEquals(errorResultVo.getErrorCode(), 468);


    }

    @Test
    void getUserProductList() throws Exception {

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/prod/list?page=1")
                .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();
        ProductUserVo[] productUserVos = om.readValue(response, ProductUserVo[].class);

        assertThat(results.size()).isEqualTo(2);
        results.forEach(r -> {
            assertThat(r.getCategory()).isEqualTo(3);
            assertThat(r.getRentalEndDate()).isEqualTo(LocalDate.of(2024, 3, 18));
        });

    }

    @Test
    void getAllReviews() {
    }
}