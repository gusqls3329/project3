package com.team5.projrental.payment;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentListVo;
import com.team5.projrental.payment.model.PaymentVo;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/pay")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResVo postPayment(@Validated @RequestBody PaymentInsDto paymentInsDto) {
        return paymentService.postPayment(paymentInsDto);
    }

    @Validated
    @DeleteMapping("/{ipay}")
    public ResVo delPayment(@PathVariable("ipay") @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE) Integer ipayment,
                            @RequestParam @Range(min = 1, max = 3, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE) Integer div) {
        return paymentService.delPayment(ipayment, div);
    }

    @Validated
    @GetMapping
    public List<PaymentListVo> getAllPayment(@RequestParam @Range(min = 1, max = 2, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE) Integer role,
                                             @RequestParam @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE) int page) {
        return paymentService.getAllPayment(role, (page - 1) * Const.PROD_PER_PAGE);
    }

    @Validated
    @GetMapping("/{ipayment}")
    public PaymentVo getPayment(@PathVariable @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE) Integer ipayment) {
        return paymentService.getPayment(ipayment);

    }

}
