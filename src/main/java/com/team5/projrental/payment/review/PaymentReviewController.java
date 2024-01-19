package com.team5.projrental.payment.review;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.review.model.DelRivewDto;
import com.team5.projrental.payment.review.model.RivewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/pay/review")
public class PaymentReviewController {
    private final PaymentReviewService reviewService;
    @PostMapping
    @Operation(summary = "리뷰 작성", description = "해당 거래의 리뷰 작성")
    @Parameters(value = {
            @Parameter(name="ipayment", description = "후기 등록하는 제품의 결제PK")
            , @Parameter(name="contents", description = "null 허용")
            , @Parameter(name="raiting", description = "null 허용 , 별점 등록 시 0~5점 등록 가능")
    })
    public ResVo postReview(@Validated @RequestBody RivewDto dto){
        return new ResVo(reviewService.postReview(dto));
    }

    @PatchMapping
    @Operation(summary = "리뷰 수정", description = "해당 거래에 등록한 리뷰 수정")
    @Parameters(value = {
            @Parameter(name="ipayment", description = "후기 등록하는 제품의 결제PK")
            , @Parameter(name="contents", description = "null 허용")
            , @Parameter(name="raiting", description = "null 허용 , 별점 등록 시 0~5점 등록 가능")
    })
    public ResVo patchReview(@Validated @RequestBody RivewDto dto){
        return new ResVo(reviewService.patchReview(dto));
    }

    @DeleteMapping
    @Operation(summary = "리뷰 삭제", description = "해당 거래에 등록한 리뷰 삭제")
    @Parameters(value = {
            @Parameter(name="ipayment", description = "후기 등록하는 제품의 결제PK")
    })
    public ResVo delReview(@Validated @RequestBody DelRivewDto dto){
        return new ResVo(reviewService.delReview(dto));
    }



}
