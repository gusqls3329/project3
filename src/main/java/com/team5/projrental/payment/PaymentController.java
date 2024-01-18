package com.team5.projrental.payment;

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
    private final PaymentService paymentService;

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
    public ResVo postPayment(@Validated @RequestBody PaymentInsDto paymentInsDto) {
        return paymentService.postPayment(paymentInsDto);
    }

    @Operation(summary = "결제 삭제 or 숨김 or 취소 요청",
            description = "<strong>결제 삭제 또는 숨김 또는 취소 요청</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "[v] ipayment: 요청할 결제의 PK<br>" +
                    "[v] div: 삭제, 숨김, 취소 요청 식별값<br>" +
                    "     ㄴ> 1: 삭제 요청, 2: 숨김 요청, 3: 취소 요청<br>" +
                    "<br>" +
                    "성공시: <br>" +
                    "result: <br>" +
                    " ㄴ> -1: 삭제됨, -2: 숨김, -3: 취소됨, 2: 제품 등록자가 취소 요청, 3: 구매자가 취소 요청<br>" +
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
                            @Range(min = 1, max = 3, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                            Integer div) {
        return paymentService.delPayment(ipayment, div);
    }

    @Operation(summary = "로그인한 유저가 구매중이거나 판매중인 결제 목록 조회",
            description = "<strong>로그인한 유저가 구매중이거나 판매중인 결제 목록 전체 조회</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "[v] role: <br>" +
                    " ㄴ> 로그인한 유저가 구매한 상품기준, 2: 로그인한 유저가 판매중인 상품들<br>" +
                    "[v] page: 페이징<br>" +
                    "<br>" +
                    "성공시: <br>" +
                    "ipayment: 결제정보 PK<br>" +
                    "iproduct: 상품 PK (해당 상품 페이지로 이동하는 용도)<br>" +
                    "title: 상품의 제목<br>" +
                    "pic: 상품의 대표 사진<br>" +
                    "price: 전체 대여 기간동안 필요한 가격<br>" +
                    "rentalStartDate: 제품 대여 시작일<br>" +
                    "rentalEndDate: 제품 반납일<br>" +
                    "deposit: 보증금" +
                    "istatus: 제품 상태<br>" +
                    " ㄴ> 0: 활성화 상태 , 1: 거래 완료됨, -2: 숨김 , -3: 취소됨 , -4: 기간지남 , 2: 제품 등록자가 취소 요청 , 3: 구매자가 취소 요청 <br>" +
                    "iuser: 거래 상대 유저의 PK<br>" +
                    "nick: 거래 상대 유저의 닉네임<br>" +
                    "pic: 거래 상대 유저의 프로필 사진<br>" +
                    "<br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @GetMapping
    public List<PaymentListVo> getAllPayment(@RequestParam
                                             @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                             @Range(min = 1, max = 2, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                                             Integer role,
                                             @RequestParam
                                             @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                             @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                                             Integer page) {
        return paymentService.getAllPayment(role, (page - 1) * Const.PROD_PER_PAGE);
    }

    @Operation(summary = "특정 결제정보 조회",
            description = "<strong>특정 결제정보 조회</strong><br>" +
                    "[ [v] : 필수값 ]" +
                    "[v] ipayment: 제품의 PK<br>" +
                    "<br>" +
                    "성공시: <br>" +
                    "ipayment: 결제의 PK<br>" +
                    "iproduct: 제품의 PK<br>" +
                    "title: 제품의 제목<br>" +
                    "pic: 제품의 대표사진<br>" +
                    "price: 전체 대여 기간동안 필요한 가격<br>" +
                    "rentalStartDate: 대여 시작 일<br>" +
                    "rentalEndDate: 제품 반납 일<br>" +
                    "rentalDuration: 제품 대여 기간<br>" +
                    "deposit: 보증금<br>" +
                    "payment: 결제 수단 -> credit-card, kakao-pay<br>" +
                    "istatus: 제품 상태<br>" +
                    "code: 제품 고유 코드<br>" +
                    "createdAt: 결제 일자<br>" +
                    "iuser: 거래 상대 유저의 PK<br>" +
                    "nick: 거래 상대 유저의 닉네임<br>" +
                    "phone: 거래 상대 유저의 핸드폰 번호<br>" +
                    "userPic: 거래 상대 유저의 프로필 사진<br>" +
                    "<br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @GetMapping("/{ipayment}")
    public PaymentVo getPayment(@PathVariable
                                @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                                Integer ipayment) {
        return paymentService.getPayment(ipayment);

    }

}
