package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.PaymentSelDto;
import com.team5.projrental.mypage.model.PaymentSelVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    @GetMapping("/{loginedIuser}")
    @Operation(summary = "대여리스트", description = "빌린내역 및 빌려준내역" +
            "<br>ibuyer에 loginedIuser가 입력되면 빌린 내역" +
            "<br>iuser에 loginedIuser가 입력되면 빌려준 내역" +
            "<br>role = 1 : loginedIuser가 빌린 내역, role = 2 : loginedIuser가 빌려준 내역")
    public List<PaymentSelVo> getPaymentList(@RequestParam(required = false, defaultValue = "0") int page,
                                             @RequestParam(name = "row_count", required = false, defaultValue = "16") int rowCount,
                                             @RequestParam(required = false) int loginedIuser,
                                             @RequestParam(required = false) int role) {

        PaymentSelDto dto = new PaymentSelDto();
        dto.setPage(page);
        dto.setRowCount(rowCount);
        dto.setLoginedIuser(loginedIuser);
        dto.setRole(role);

        return service.paymentList(dto);
    }
}
