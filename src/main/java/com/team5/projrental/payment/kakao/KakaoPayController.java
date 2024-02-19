package com.team5.projrental.payment.kakao;

import com.team5.projrental.payment.kakao.model.logic.PayApproveVo;
import com.team5.projrental.payment.kakao.model.logic.PayReadyVo;
import com.team5.projrental.payment.kakao.model.ready.PayInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.DependentRequiredMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/pay/kakao")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    @Operation(summary = "aseseg",
            description = "asegseg")
    @Schema(name = "kakao")
    @GetMapping("/ready")
    public PayReadyVo ready(@RequestBody PayInfoDto dto) {
        return kakaoPayService.ready(dto);
    }

    @Operation(summary = "aseseg",
            description = "asegseg")
    @Schema(name = "kakao2")
    @GetMapping("/success/{uuid}")
    public PayApproveVo approve(@PathVariable String uuid,
                                @RequestParam("pg_token") String pgToken) {
        return kakaoPayService.approve(pgToken, uuid);
    }

}
