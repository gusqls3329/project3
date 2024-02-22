package com.team5.projrental.payment;

import com.team5.projrental.admin.model.paymentInfoVo;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentListVo;
import com.team5.projrental.payment.model.PaymentVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    private final RefPaymentService paymentService;

    @Operation(summary = "결제 정보 등록",
            description = "<strong>결제 정보 등록</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "[v] iproduct: 결제한 제품의 PK<br>" +
                    "[v] rentalStartDate: 실제 계약된 제품 대여 시작일<br>" +
                    "[v] rentalEndDate: 실제 계약된 제품 대여 마감일<br>" +
                    "[v] paymentMethod: 결제 수단 -> credit-card, kakao-pay<br>" +
                    "<br>" +
                    "성공시: <br>" +
                    "result: 1" +
                    "<br><br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @PostMapping
    public ResVo postPayment(@Validated @RequestBody PaymentInsDto dto) {
        return paymentService.postPayment(dto);
    }

    @Operation(summary = "결제 삭제 or 숨김 or 취소 요청",
            description = "<strong>결제 삭제 또는 숨김 또는 취소 요청</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "[v] ipayment: 요청할 결제의 PK<br>" +
                    "[v] div: 삭제, 숨김, 취소 요청 식별값<br>" +
                    "     ㄴ> 1: 삭제 요청, 3: 취소 요청<br>" +
                    "<br>" +
                    "성공시: <br>" +
                    "result: <br>" +
                    " ㄴ> -1: 삭제됨, -3: 취소됨<br>" +
                    "<br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @DeleteMapping("/{ipay}")
    public ResVo delPayment(@PathVariable("ipay")
                            @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                            @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                            Integer ipayment,
                            @RequestParam
                            @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                            @Range(min = 1, max = 3, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                            Integer div) {
        return paymentService.delPayment(ipayment, div);
    }


    @Operation(summary = "특정 결제의 결제정보 조회")
    @GetMapping("/{ipayment}")
    public paymentInfoVo getPaymentInfo(@PathVariable long ipayment) {
        return null;
    }
}
