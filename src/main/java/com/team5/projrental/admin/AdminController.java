package com.team5.projrental.admin;

import com.team5.projrental.admin.model.GetUsersListVo;
import com.team5.projrental.admin.model.ProfitDto;
import com.team5.projrental.admin.model.ProfitVo;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    @GetMapping("/profit")
    @Operation(summary = "한달간 받은 수익", description = "광고로 받은 수익 표시")
    public ProfitVo getProfit(@RequestBody ProfitDto dto){
        return service.getProfit(dto);
    }




    @Operation(summary = "유저 특정 결제정보 조회",
            description = "<strong>유저 특정 결제정보 조회</strong><br>" +
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
                    "role: 로그인한 유저가 판매자인지 구매자인지 여부 (1: 판매자, 2: 구매자) - 리뷰 분기용" +
                    "createdAt: 결제 일자<br>" +
                    "iuser: 거래 상대 유저의 PK<br>" +
                    "nick: 거래 상대 유저의 닉네임<br>" +
                    "phone: 거래 상대 유저의 핸드폰 번호<br>" +
                    "userPic: 거래 상대 유저의 프로필 사진<br>" +
                    "<br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @GetMapping("/ipayment")
    public PaymentVo getPayment(@PathVariable
                                @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                                long code) {
        return null;
    }



    @DeleteMapping("/delete")
    public ResVo delBoard(@PathVariable
                              @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                              @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                              long iboard) {

        return null;
    }

    @GetMapping("/user")
    public List<GetUsersListVo> getAllUsers (long page) {

        return null;
    }









}
