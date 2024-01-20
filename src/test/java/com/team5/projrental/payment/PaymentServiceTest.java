package com.team5.projrental.payment;

import com.team5.projrental.common.exception.base.BadDateInfoException;
import com.team5.projrental.common.exception.NoSuchProductException;
import com.team5.projrental.common.exception.NoSuchUserException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.proc.GetInfoForCheckIproductAndIuserResult;
import com.team5.projrental.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@Import(PaymentService.class)
class PaymentServiceTest {


    @MockBean
    PaymentRepository paymentRepository;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    AuthenticationFacade authenticationFacade;
    @MockBean
    MyFileUtils myFileUtils;


/*    @Autowired
    PaymentService paymentService;

    @Test
    void postPayment() {
        PaymentInsDto mockData = PaymentInsDto.builder()
                .iproduct(1)
                .paymentMethod("kakao-pay")
                .rentalStartDate(LocalDate.of(2025, 1, 1))
                .rentalEndDate(LocalDate.of(2025, 3, 3))
                .depositPer(80).build();

        when(productRepository.findRentalPriceBy(any())).thenReturn(1000);
        when(authenticationFacade.getLoginUserPk()).thenReturn(3);
        when(productRepository.findIuserCountBy(any())).thenReturn(true);
        when(paymentRepository.savePayment(any())).thenReturn(1);
        when(paymentRepository.saveProductPayment(any(), any())).thenReturn(1);


        ResVo resVo = paymentService.postPayment(mockData);
        assertThat(resVo.getResult()).isEqualTo(1);

        PaymentInsDto mockData2 = PaymentInsDto.builder()
                .iproduct(1)
                .paymentMethod("kakao-pay")
                .rentalStartDate(LocalDate.of(2025, 1, 1))
                .rentalEndDate(LocalDate.of(2024, 3, 3))
                .depositPer(80).build();

        assertThatThrownBy(() -> paymentService.postPayment(mockData2))
                .isInstanceOf(BadDateInfoException.class);

        when(productRepository.findIuserCountBy(any())).thenReturn(false);
        assertThatThrownBy(() -> paymentService.postPayment(mockData2))
                .isInstanceOf(NoSuchUserException.class);
        when(productRepository.findRentalPriceBy(any())).thenReturn(0);
        assertThatThrownBy(() -> paymentService.postPayment(mockData2))
                .isInstanceOf(NoSuchProductException.class);
    }*/

    @Test
    void delPayment() {
        GetInfoForCheckIproductAndIuserResult obj1 =
                new GetInfoForCheckIproductAndIuserResult(1, LocalDate.of(2025, 1, 1), 1, 2);

        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        when(paymentRepository.checkIuserAndIproduct(any())).thenReturn(obj1);


    }

    @Test
    void getAllPayment() {
    }

    @Test
    void getPayment() {
    }
}