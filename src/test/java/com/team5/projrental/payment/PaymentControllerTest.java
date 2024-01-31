package com.team5.projrental.payment;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentListVo;
import com.team5.projrental.payment.model.PaymentVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(PaymentController.class)
class PaymentControllerTest {

    @MockBean
    PaymentService paymentService;

    @Autowired
    PaymentController paymentController;

    @Test
    void postPayment() {


        when(paymentService.postPayment(any())).thenReturn(new ResVo(1));
        assertThat(paymentController.postPayment(PaymentInsDto.builder().build()).getResult()).isEqualTo(1);

    }

    @Test
    void delPayment() {


        when(paymentService.delPayment(any(), any())).thenReturn(new ResVo(1));
        assertThat(paymentController.delPayment(1, 1).getResult()).isEqualTo(1);

    }
}