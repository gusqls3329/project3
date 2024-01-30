package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.*;
import com.team5.projrental.product.model.review.ReviewResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {
    private final MypageService service;

    //테스트 상원
    @Validated
    @GetMapping("/prod")
    @Operation(summary = "대여리스트", description = "대여관련 내역")
    @Parameters(value = {
            @Parameter(name = "page", description = "페이지"),
            @Parameter(name = "role", description = "role : 1 = 빌린 내역, 2 = 빌려준 내역")})
    public List<PaymentSelVo> getPaymentList(@RequestParam int page, @RequestParam int role) {
        PaymentSelDto dto = new PaymentSelDto();
        dto.setPage(page);
        dto.setRole(role);
        return service.paymentList(dto);
    }

    @Validated
    @GetMapping("/review")
    @Operation(summary = "로그인 유저가 작성한 후기", description = "로그인 유저가 빌린내역 중 작성된 후기")
    @Parameters(value = {@Parameter(name = "page", description = "페이지")})
    public List<MyBuyReviewListSelVo> getReview(@RequestParam @Min(1) int page) {
        MyBuyReviewListSelDto dto = new MyBuyReviewListSelDto();
        dto.setPage(page);
        return service.selIbuyerReviewList(dto);
    }

    @Validated
    @GetMapping("/fav")
    @Operation(summary = "로그인 유저가 찜한 목록", description = "로그인 유저가 찜한 목록")
    @Parameters(value = {@Parameter(name = "page", description = "페이지")})
    public List<MyFavListSelVo> getFavList(@RequestParam @Min(1) int page) {
        MyFavListSelDto dto = new MyFavListSelDto();
        dto.setPage(page);
        return service.selMyFavList(dto);
    }

    @GetMapping("/prod/review")
    @Operation(summary = "로그인한 유저가 받은 모든 제품의 모든 리뷰", description = "로그인한 유저가 받은 모든 제품의 모든 리뷰")
    public List<ReviewResultVo> getAllReviewFromMyProduct() {
        return service.getAllReviewFromMyProduct();
    }
}
