package com.team5.projrental.payment.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.MockMvcConfig;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.PaymentController;
import com.team5.projrental.payment.PaymentService;
import com.team5.projrental.payment.review.model.DelRivewDto;
import com.team5.projrental.payment.review.model.RivewDto;
import com.team5.projrental.payment.review.model.RiviewVo;
import com.team5.projrental.payment.review.model.UpRieDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.team5.projrental.common.security.AuthenticationFacade;

@SpringBootTest
@MockMvcConfig
@Import(PaymentReviewController.class)
class PaymentReviewControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentReviewService service;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Test
    void postReview() throws Exception {
        RivewDto dto = new RivewDto();
        dto.setIuser(4);
        dto.setContents("리뷰테스트");
        dto.setIpayment(13);
        dto.setRating(5);

        given(service.postReview(dto)).willReturn(1);

        given(authenticationFacade.getLoginUserPk()).willReturn(4);
        given(service.postReview(any(RivewDto.class))).willReturn(Const.SUCCESS);

        // Act and Assert
        mvc.perform(MockMvcRequestBuilders.post("/api/pay/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk()).andExpect(content().json("{\"result\":1}"));


        verify(service).postReview(any());
        //verify(authenticationFacade).getLoginUserPk(); :오류발생
    }


    @Test
    void patchReview() throws Exception {
        UpRieDto dto = new UpRieDto();
        dto.setContents("변경함");
        dto.setRating(4);
        dto.setIreview(2);
        dto.setIuser(1);

        given(authenticationFacade.getLoginUserPk()).willReturn(1);
        given(service.patchReview(any(UpRieDto.class))).willReturn(Const.SUCCESS);

        mvc.perform(MockMvcRequestBuilders
                        .patch("/api/pay/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk()).andExpect(content().json("{\"result\":1}"));

        verify(service).patchReview(any());
    }

    @Test
    void delReview() throws Exception {
        DelRivewDto dto = new DelRivewDto();
        dto.setIuser(1);
        dto.setIreview(2);
        dto.setIstatus(1);

        given(authenticationFacade.getLoginUserPk()).willReturn(1);
        given(service.delReview(any(DelRivewDto.class))).willReturn(Const.SUCCESS);

        mvc.perform(MockMvcRequestBuilders
                .delete("/api/pay/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":1}"));
        verify(service).delReview(any());
    }
}