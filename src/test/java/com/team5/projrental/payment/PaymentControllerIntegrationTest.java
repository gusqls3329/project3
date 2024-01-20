package com.team5.projrental.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.exception.BadDivInformationException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class PaymentControllerIntegrationTest {
    /* TODO: 1/16/24
        파싱 에러, @Validated 에러 ExceptionResolver 에 추가할것.
        --by Hyunmin */

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    String authValue;


    @BeforeEach
    void before() throws Exception {
        System.out.println("mockMvc = " + mockMvc);
        System.out.println("objectMapper = " + objectMapper);
        UserSignupDto userSignupDto = new UserSignupDto();
        userSignupDto.setAddr("대구 달서구 용산1동");
        userSignupDto.setRestAddr("아아아");
        userSignupDto.setUid("test111111");
        userSignupDto.setUpw("test111111");
        userSignupDto.setNick("test111111");
        userSignupDto.setPhone("010-1111-1124");
        userSignupDto.setEmail("efa@gejrrr.com");
        String result2 = objectMapper.writeValueAsString(userSignupDto);
        ResultActions perform = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result2))
                .andExpect(MockMvcResultMatchers.status().isOk());

//        perform.andExpect(MockMvcResultMatchers.status().isOk());

        SigninDto signinDto = new SigninDto();
        signinDto.setUid("test111111");
        signinDto.setUpw("test111111");


        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signinDto))
        ).andReturn();

        this.authValue =
                "Bearer " + objectMapper.readValue(result.getResponse().getContentAsString(), SigninVo.class).getAccessToken();

    }

    @Test
    void postPayment() throws Exception {


        PaymentInsDto dto = PaymentInsDto.builder()
                .iproduct(2)
                .paymentMethod("credit-card")
                .rentalStartDate(LocalDate.of(2025, 1, 2))
                .rentalEndDate(LocalDate.of(2025, 3, 3))
                //.depositPer(51)
                .build();

        String json = objectMapper.writeValueAsString(dto);
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/pay")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                                .header("Authorization",
                                        this.authValue)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), ResVo.class).getResult()).isEqualTo(1);
    }

    @Test
    void delPayment() throws Exception {

        // 5번으로 로그인
        assertThatThrownBy(() -> objectMapper.readValue(mockMvc.perform(
                                MockMvcRequestBuilders.delete("/api/pay/1?div=1")
                                        .header("Authorization",
                                                this.authValue)
                        ).andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString(), ResVo.class)
        ).isInstanceOf(BadDivInformationException.class);
        // 2번으로 로그인
        assertThat(objectMapper.readValue(mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/pay/2?div=1")
                                .header("Authorization",
                                        this.authValue)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString(), ResVo.class).getResult()).isEqualTo(-1);
        // 3번으로 로그인
        assertThatThrownBy(() -> objectMapper.readValue(mockMvc.perform(
                                MockMvcRequestBuilders.delete("/api/pay/3?div=1")
                                        .header("Authorization",
                                                this.authValue)
                        ).andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString(), ResVo.class)
        ).isInstanceOf(BadDivInformationException.class);
        // 2번으로 로그인
        assertThat(objectMapper.readValue(mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/pay/4?div=1")
                                .header("Authorization",
                                        this.authValue)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString(), ResVo.class).getResult()).isEqualTo(-1);
        // 1번으로 로그인
        assertThat(objectMapper.readValue(mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/pay/5?div=3")
                                .header("Authorization",
                                        this.authValue)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString(), ResVo.class).getResult()).isEqualTo(2);



        // 2번으로 로그인
        assertThat(objectMapper.readValue(mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/pay/2?div=2")
                                .header("Authorization",
                                        this.authValue)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString(), ResVo.class).getResult()).isEqualTo(-2);

    }

    @Test
    void getAllPayment() {
    }

    @Test
    void getPayment() {
    }
}