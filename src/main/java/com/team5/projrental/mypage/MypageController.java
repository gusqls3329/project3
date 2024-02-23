package com.team5.projrental.mypage;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.mypage.model.*;
import com.team5.projrental.product.model.review.ReviewResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

import static com.team5.projrental.common.exception.ErrorMessage.BAD_SORT_EX_MESSAGE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {
    private final MypageService service;

    @Validated
    @GetMapping("/prod")
    @Operation(summary = "대여리스트", description = "대여관련 내역")
    @Parameters(value = {
            @Parameter(name = "status", description = "특정 상태인 결제 조회 (예약중, 거래시작, 만료됨, 거래완료, 예약취소, 숨김 조회 가능)\n"+
                    "로그인유저의 결제정보를 조회한다는것을 잊으면 안됨."),
            @Parameter(name = "page", description = "페이지")})
    public List<PaymentSelVo> getRentalList(@RequestParam(name = "status", required = false) int status,
                                            @RequestParam(defaultValue = "1") @Range(min = 1) int page) {
        PaymentSelDto dto = new PaymentSelDto();
        dto.setPage(page);
        return service.getRentalList(dto);
    }


    /*@Validated
    @Operation(summary = "대여리스트", description = "로그인 유저가 ")
    @Parameters(value = {@Parameter(name = "page", description = "페이지"),
            @Parameter(name = "role", description = "role:1 -> iuser 가 구매한 상품들\n" +
                    "role:2 -> iuser 가 판매한 상품들"),
            @Parameter(name = "status", description = "1:예약중 <br> 6까지 설명작성예정")
    })
    @GetMapping("/prod")
    public List<PaymentSelVo> getRentalList(long role, @Range(min = 1) @PageableDefault(page = 1, size = 10)Pageable pageable, long status) {
        return null;
    }*/

    @Validated
    @GetMapping("/fav")
    @Operation(summary = "관심 목록", description = "로그인한 유저가 관심 등록한 제품 리스트")
    @Parameters(value = {@Parameter(name = "page", description = "페이지")})
    public List<MyFavListSelVo> getFavList(@RequestParam(defaultValue = "1") @PageableDefault(page = 1, size = 10)Pageable pageable) {

        return null;
    }


    @Validated
    @GetMapping("/review")
    @Operation(summary = "작성한 후기", description = "로그인 유저가 빌린내역 중 작성한 후기 리스트")
    @Parameters(value = {@Parameter(name = "page", description = "페이지")})
    public List<MyBuyReviewListSelVo> getReview(@RequestParam(defaultValue = "1") @PageableDefault(page = 1, size = 10)Pageable pageable) {
        MyBuyReviewListSelDto dto = new MyBuyReviewListSelDto();
        return service.getReview(dto);
    }

    @Validated
    @GetMapping("/dispute")
    @Operation(summary = "신고한 목록", description = "로그인 유저가 신고한 목록")
    @Parameters(value = {@Parameter(name = "page", description = "페이지")})
    public List<MyDisputeVo> getDispute(@RequestParam(defaultValue = "1") @PageableDefault(page = 1, size = 10)Pageable pageable) {

        return null;
    }

    @PatchMapping("/dispute")
    @Operation(summary = "신고 철회", description = "로그인 유저가 신고한 목록")
    @Parameters(value = {@Parameter(name = "idispute", description = "철회 할 분쟁pk")})
    public ResVo cancelDispute(@RequestBody int idispute) {
        return null;
    }


    @Validated
    @GetMapping("/board")
    @Operation(summary = "내가 쓴 게시글", description = "내가 작성한 자유게시글 조회")
    @Parameters(value = {@Parameter(name = "page", description = "페이지")})
    public List<MyBoardListVo> getBoard(@RequestParam(defaultValue = "1") @Min(1) int page) {
        return null;
    }
}
