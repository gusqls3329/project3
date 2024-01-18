package com.team5.projrental.product;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.model.ErrorResultVo;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.regex.qual.Regex;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.team5.projrental.common.exception.ErrorMessage.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/prod")
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "특정 카테고리의 제품목록 가져오기",
            description = "<strong>제품목록 조회</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "sort: 정렬 기준<br> " +
                    "1: like 순으로 조회<br>" +
                    "2: 조회 많은순으로 보기 <br>" +
                    "sort 가 제공되지 않으면 최신순으로 조회<br><br>" +
                    "search: 검색어 조회 (특정 단어가 제목에 포함된 제품목록 조회<br><br>" +
                    "[v] icategory: 조회할 카테고리, <strong>필수값</strong><br><br>" +
                    "[v] page: 페이징" +
                    "<br><br>" +
                    "성공시:<br>" +
                    "[nick, userPic, iproduct, title, prodPic, rentalPrice, rentalStartDate, rentalEndDate, addr(rest_addr 까지 " +
                    "포함), prodLike(제품의 좋아요 수)] (배열)" +
                    "<br><br>" +
                    "실패시:<br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @GetMapping("{icategory}")
    public List<ProductListVo> getProductList(@RequestParam(required = false)
                                              @Range(min = 1, max = 2, message = BAD_SORT_EX_MESSAGE)
                                              Integer sort,
                                              @RequestParam(required = false) String search,
                                              @RequestParam @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                                              @Min(value = 1, message = ILLEGAL_RANGE_EX_MESSAGE)
                                              Integer page,
                                              @PathVariable
                                              @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                                              @Min(value = 1, message = ILLEGAL_RANGE_EX_MESSAGE)
                                              Integer icategory) {

        return productService.getProductList(sort, search, icategory, (page - 1) * Const.PROD_PER_PAGE);
    }

    //

    /* TODO: 1/17/24
        로그인한 유저가 해당 제품을 좋아요 했는지 여부 체크하는 데이터도 리턴에 추가해야함.
        --by Hyunmin */
    @Operation(summary = "특정 상품 상세 조회",
            description = "<strong>특정 상품의 상세페이지 조회</strong><br>" +
                    "[ [v] : 필수값]<br>" +
                    "[v] icategory: 조회할 상품의 카테고리<br>" +
                    "[v] iproduct: 조회할 상품의 iproduct 값 (getProductList 에서 제공됨)<br><br>" +
                    "성공시:<br>" +
                    "iuser(판매자의), nick(판매자의), userPic(판매자의), title, contents, mainPic, pics(배열), deposit, " +
                    "buyDate, rentalStartDate, rentalEndDate, addr (rest_addr 까지 포함), prodLike, x, y" +
                    "<br><br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @GetMapping("/api/prod/{icategory}/{iproduct}")
    public ProductVo getProduct(@PathVariable
                                @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                                @Min(value = 1, message = ILLEGAL_RANGE_EX_MESSAGE)
                                Integer icategory,
                                @PathVariable
                                @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                                @Min(value = 1, message = ILLEGAL_RANGE_EX_MESSAGE)
                                Integer iproduct) {
        return productService.getProduct(icategory, iproduct);
    }

    /*
    pics + mainPic 개수 검증 - 10개 이하 -> iuser 가 존재하는지 검증 -> category 존재여부 검증 ->
    price 양수 검증 -> buyDate 오늘보다 이전인지 검증 -> depositPer 70 이상 100 이하 검증 ->
    오늘이 rentalStartDate 보다 이전이 아닌지 검증 -> rentalEndDate 가 rentalStartDate 보다 이전이 아닌지 검증 ->
    depositPer 를 price 기준 퍼센트 금액으로 환산 ->  본 로직
    -> addr + restAddr 기준으로 x, y 좌표 획득 -> insert model 객체 생성 -> insert
     */

    @Operation(summary = "제품 등록",
            description = "<strong>제품 등록</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "[v] title: 제품의 제목 -> null, \"\", \" \" 불가능<br>" +
                    "[v] contents: 제품 내용 -> null, \"\", \" \" 불가능<br>" +
                    "[v] addr: 거래 가능 주소 -> null, \"\", \" \" 불가능<br>" +
                    "[v] restAddr: 거래 가능 나머지 주소 -> null, \"\", \" \" 불가능<br>" +
                    "[v] mainPic: 대표사진<br>" +
                    "pic: 나머지 사진<br>" +
                    "[v] price: 자신이 평가한 제품의 절대가격 -> null 불가능<br>" +
                    "[v] rentalPrice: 1일당 대여 가격 -> null 불가능<br>" +
                    "[v] depositPer: 보증금 가격 비율 (price 기준 비율) -> null 불가능<br>" +
                    "buyDate: 자신이 제품을 구매한 날짜<br>" +
                    "rentalStartDate: 대여 시작 가능 날짜<br>" +
                    "rentalEndDate: 대여 마감 날짜<br>" +
                    "icategory: 카테고리 (숫자) -> null 불가능, 1 이상" +
                    "<br><br>" +
                    "성공시: <br>" +
                    "result: 등록된 제품의 PK" +
                    "<br><br>" +
                    "실패시:<br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @PostMapping
    public ResVo postProduct(@RequestPart
                             @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
                             MultipartFile mainPic,
                             @RequestPart(required = false)
                             List<MultipartFile> pics,
                             @Validated
                             @RequestPart
                             ProductInsDto dto) {

        return productService.postProduct(mainPic, pics, dto);


    }

    @Operation(summary = "특정 제품 수정",
            description = "<strong>특정 제품 수정</strong><br>" +
                    "[ [v] : 필수값 -> 필수값이 아닌 데이터들은 수정할 경우에 수정된 데이터만 제공, 수정되지 않은 데이터는 제공하지 않아도 됨.]<br>" +
                    "[v] iproduct: 수정할 제품의 PK<br>" +
                    "icategory: 수정할 카테고리 (숫자) -> null 불가능, 1 이상<br>" +
                    "addr: 수정할 거래 가능 주소<br>" +
                    "restAddr: 수정할 거래 가능 나머지 주소<br>" +
                    "title: 수정할 제품 제목<br>" +
                    "contents: 수정할 제품 내용<br>" +
                    "mainPic: 수정할 대표 사진<br>" +
                    "pic: 수정할 사진<br>" +
                    "price: 수정할 제품 가격<br>" +
                    "rentalPrice: 1일당 대여 가격<br>" +
                    "depositPer: 수정할 보증금 가격 비율 (price 가 수정되면 수정된 price 기준, 수정되지 않으면 이미 저장된 price 기준<br>" +
                    "buyDate: 수정할 자신이 제품을 구매한 날짜<br>" +
                    "rentalStartDate: 수정할 대여 시작 가능 날짜<br>" +
                    "rentalEndDate: 수정할 대여 마감 날짜<br>" +
                    "delPics: 삭제된 사진들의 ipics 값들 -> 제품 조회시 사진에대한 ipics 값들이 제공됨. <br>  " +
                    "  ㄴ> 메인사진은 삭제는 불가능하고 변경만 가능함으로 delPics 에 기재하지 " +
                    "않고, 변경된" +
                    " 사진만 넘기면 됨." +
                    "<br><br>" +
                    "성공시: <br>" +
                    "result: 1<br><br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")

    @PutMapping
    public ResVo putProduct(@RequestPart(required = false) MultipartFile mainPic,
                            @RequestPart(required = false) List<MultipartFile> pics,
            @Validated @RequestPart ProductUpdDto dto) {

        return productService.putProduct(mainPic, pics, dto);
    }

    @Operation(summary = "제품 수정 or 삭제",
            description = "<strong>제품의 수정 또는 삭제 요청</strong><br>" +
                    "[ [v] : 필수값 ]<br>" +
                    "[v] iproduct: 수정 또는 삭제 요청할 제품의 PK<br>" +
                    "[v] div: 삭제 또는 수정 요청 식별값<br>" +
                    "     ㄴ> 1: 삭제 요청, 2: 숨김 요청" +
                    "<br><br>" +
                    "성공시: <br>" +
                    "result: 1<br><br>" +
                    "실패시:<br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @DeleteMapping("/{iproduct}")
    public ResVo delProduct(@PathVariable
                            @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                            @Min(value = 1, message = ILLEGAL_RANGE_EX_MESSAGE)
                            Integer iproduct,
                            @RequestParam
                            @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                            @Range(min = 1, max = 2, message = ILLEGAL_RANGE_EX_MESSAGE)
                            Integer div) {
        return productService.delProduct(iproduct, div);
    }

    @Operation(summary = "특정 유저의 모든 제품 목록 조회",
            description = "<strong>특정 유저의 모든 제품목록 조회</strong><br>" +
                    "[ [v] : 필수값]<br>" +
                    "[v] page: 페이징" +
                    "<br><br>" +
                    "성공시: <br>" +
                    "nick, userPic, iproduct, category, title, prodPic, rentalPrice, rentalStartDate, " +
                    "rentalEndDate, addr (rest_addr 까지 포함) (배열) <br><br>" +
                    "실패시: <br>" +
                    "message: 에러 발생 사유<br>errorCode: 에러 코드")
    @Validated
    @GetMapping("/list")
    public List<ProductUserVo> getUserProductList(@RequestParam
                                                  @NotNull(message = CAN_NOT_BLANK_EX_MESSAGE)
                                                  @Min(value = 1, message = ILLEGAL_RANGE_EX_MESSAGE)
                                                  @NotNull Integer page) {
        return productService.getUserProductList((page - 1) * Const.PROD_PER_PAGE);

    }
}
