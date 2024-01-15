package com.team5.projrental.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaymentControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void postPayment() throws Exception {
        PaymentInsDto dto = PaymentInsDto.builder()
                .iproduct(1)
                .paymentMethod("credit-card")
                .rentalStartDate(LocalDate.of(2025, 1, 2))
                .rentalEndDate(LocalDate.of(2025, 3, 3))
                .deposit(50)
                .build();


        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/pay").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), ResVo.class).getResult()).isEqualTo(1);
    }

    @Test
    void delPayment() {
    }

    @Test
    void getAllPayment() {
    }

    @Test
    void getPayment() {
    }
}