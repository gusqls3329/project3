package com.team5.projrental.payment;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/pay")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResVo postPayment(PaymentInsDto paymentInsDto) {
        return paymentService.postPayment(paymentInsDto);
    }

    @Validated
    @DeleteMapping("/{ipay}/{iuser}")
    public ResVo delPayment(@PathVariable("ipay") @Min(0) Integer ipayment,
                            @PathVariable("iuser") @Min(0) Integer iuser,
                            @RequestParam @Range(min = 1, max = 3) Integer div) {
        return paymentService.delPayment(ipayment, iuser, div);
    }
}
