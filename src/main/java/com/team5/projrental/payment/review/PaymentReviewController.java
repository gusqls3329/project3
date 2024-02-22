package com.team5.projrental.payment.review;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.review.model.DelRivewDto;
import com.team5.projrental.payment.review.model.RivewDto;
import com.team5.projrental.payment.review.model.UpRieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/pay/review")
public class PaymentReviewController {
    /* TODO 2024-01-22 Mon 17:45
        리뷰 등록할 때 해당 제품의 판매자 의 rating (t_user 의) 평균 내서 update 하는 로직 추가하기
        --by Hyunmin
    */


    private final PaymentReviewService reviewService;
    @PostMapping
    @Operation(summary = "리뷰 작성", description = "해당 거래의 리뷰 작성")
    @Parameters(value = {
            @Parameter(name="ipayment", description = "필수 : 후기 등록하는 제품의 결제PK")
            , @Parameter(name="contents", description = "(contents와rating 둘중하나는 필수)구매자일 경우 not null, 판매자일 경우 null 허용")
            , @Parameter(name="rating", description = "(contents와rating 둘중하나는 필수)null 허용(null일경우 별점을 등록하지 않음) , 별점 등록 시 1~5점 등록 가능")
    })
    public ResVo postReview(@Validated @RequestBody RivewDto dto){
        return new ResVo(reviewService.postReview(dto));
    }

    @PatchMapping
    @Operation(summary = "리뷰 수정", description = "해당 거래에 등록한 리뷰 수정<br>")
    @Parameters(value = {
            @Parameter(name="ireview", description = "필수 : 작성한 리뷰 PK")
            , @Parameter(name="contents", description = "(contents와rating 둘중하나는 필수) 구매자일 경우 not null, 판매자일 경우 null 허용")
            , @Parameter(name="rating", description = "(contents와rating 둘중하나는 필수)null 허용(null일경우 별점을 등록하지 않음), 별점 등록 시 1~5점 등록 가능")
    })
    public ResVo patchReview(@Validated @RequestBody UpRieDto dto){
        return new ResVo(reviewService.patchReview(dto));
    }

    @DeleteMapping
    @Operation(summary = "리뷰 삭제", description = "해당 거래에 등록한 리뷰 삭제(상태만 변경예정)")
    @Parameters(value = {
            @Parameter(name="ireview", description = "필수 : 작성한 리뷰 PK")
    })
    public ResVo delReview(@Validated @RequestParam("rev") int ireview){
        DelRivewDto dto = new DelRivewDto();
        dto.setIreview(ireview);
        return new ResVo(reviewService.delReview(dto));
    }


}
