package com.team5.projrental.admin;

import com.team5.projrental.admin.model.*;
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


    @Operation(summary = "자유게시글 강제 삭제",
            description = "<strong>신고당한 자유게시글이나 잘못된 게시글 강제 삭제 가능함, sse 푸시를 위해 이벤트 발행 해야함!!!</strong><br>")
    @Validated
    @DeleteMapping("/board")
    public ResVo delBoard(@PathVariable
                              @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                              @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
                              long iboard) {

        return null;
    }

    @Validated
    @Operation(summary = "전체 *일반* 유저목록 조회",
            description = "전체 *일반* 유저목록 조회</strong><br>" )
    @GetMapping("/user")
    public List<GetUsersListVo> getAllUsers (@PathVariable
                                                 @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                                 @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)long page) {

        return null;
    }




    @Operation(summary = "회원 강제 탈퇴",
            description = "(자동화는 별도로 존재) 운영자 재량으로 유저 탈퇴, sse 푸시를 위해 이벤트 발행 해야함!!!" )
    @DeleteMapping("/user")
    @Validated
    public ResVo delUser (@PathVariable
                                             @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                             @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)long iuser) {

        return null;
    }

    @Operation(summary = "전체 신고내역 조회",
            description = "전체 신고내역 조회" )
    @GetMapping("/dispute")
    @Validated
    public List<GetDisputeListVo> getAllDisputes (@PathVariable
                                         @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                         @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)long page) {

        return null;
    }

    @Operation(summary = "수리 또는 반려",
            description = "신고 당한 유저의 내용 파악후 수락, sse 푸시를 위해 이벤트 발행 해야함!!!" )
    @PostMapping("/dispute")
    @Validated
    public List<GetUsersListVo> postDispute (@PathVariable
                                                @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                                @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)long page, @RequestParam postDispute dto) {

        return null;
    }

    @Operation(summary = "운영자 전체제품 조회",
            description = "운영자 페이지에서 전체유저의 상품 조회" )
    @GetMapping("/product")
    @Validated
    public List<GetProductListVo> getAllProducts (@PathVariable
                                             @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                             @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)long page) {

        return null;
    }

    @Operation(summary = "상품 강제 숨김 처리",
            description = "운영자가 해당 상품을 강제 삭제(숨김처리), sse 푸시를 위해 이벤트 발행 해야함!!!" )
    @DeleteMapping("/product")
    @Validated
    public ResVo patchProduct (@PathVariable
                                                  @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                                                  @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)long iproduct) {

        return null;
    }




}
