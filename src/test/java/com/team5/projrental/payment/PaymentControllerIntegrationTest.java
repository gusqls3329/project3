package com.team5.projrental.payment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.exception.BadDivInformationException;
import com.team5.projrental.common.model.ErrorResultVo;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentListVo;
import com.team5.projrental.payment.model.PaymentVo;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import com.team5.projrental.user.model.UserSignupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class PaymentControllerIntegrationTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    String authValue;

    @Autowired
    ObjectMapper om;
    String token;

    MultipartFile multipartFile;


    @BeforeEach
    void before() throws Exception {

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
    void postPayment() throws Exception {

        PaymentInsDto paymentInsDto = new PaymentInsDto();
        paymentInsDto.setIproduct(15);
        paymentInsDto.setIbuyer(8);
        paymentInsDto.setPaymentMethod("credit-card");
        paymentInsDto.setRentalStartDate(LocalDate.of(2025, 5, 5));
        paymentInsDto.setRentalEndDate(LocalDate.of(2026, 10, 11));


        ResVo resVo = om.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/api/pay")
                        .header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(paymentInsDto)))
                .andReturn().getResponse().getContentAsString(), ResVo.class);

        assertThat(resVo.getResult()).isEqualTo(1);
    }

    @Test
    void delPayment() throws Exception {

        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/pay/10?div=3")
                        .header("Authorization", this.token))
                .andReturn().getResponse().getContentAsString();
        ResVo resVo = om.readValue(response, ResVo.class);

        assertThat(resVo.getResult()).isEqualTo(2);

        String response2 = mockMvc.perform(MockMvcRequestBuilders.delete("/api/pay/8?div=1")
                        .header("Authorization", this.token))
                .andReturn().getResponse().getContentAsString();
        ResVo errorResultVo = om.readValue(response2, ResVo.class);
        assertThat(errorResultVo.getResult()).isEqualTo(-1);

        System.out.println("---sep---");

        String response3 = mockMvc.perform(MockMvcRequestBuilders.delete("/api/pay/10?div=1")
                        .header("Authorization", this.token))
                .andReturn().getResponse().getContentAsString();
        ErrorResultVo errorResultVo1 = om.readValue(response3, ErrorResultVo.class);
        assertThat(errorResultVo1.getErrorCode()).isEqualTo(436);
    }

    @Test
    void getAllPayment() throws Exception {
        List<PaymentListVo> response = om.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/api/pay?role=1&page=1")
                        .header("Authorization", this.token))
                .andReturn().getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(response.size()).isEqualTo(1);
        assertThat(response.get(0).getIuser()).isEqualTo(7);


        response = om.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/api/pay?role=2&page=1")
                        .header("Authorization", this.token))
                .andReturn().getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(response.size()).isEqualTo(6);
        assertThat(response.get(0).getIuser()).isEqualTo(3);
        assertThat(response.get(1).getIuser()).isEqualTo(3);


    }

    @Test
    void getPayment() throws Exception {

        PaymentVo result = om.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/api/pay/11")
                        .header("Authorization", this.token))
                .andReturn().getResponse().getContentAsString(), PaymentVo.class);

        assertThat(result.getPhone()).isEqualTo("010-4444-4444");
        assertThat(result.getCode()).isEqualTo("f6171705646694944336");
        assertThat(result.getIuser()).isEqualTo(4);

    }
}