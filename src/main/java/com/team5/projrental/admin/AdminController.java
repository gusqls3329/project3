package com.team5.projrental.admin;

import com.team5.projrental.admin.model.ProfitDto;
import com.team5.projrental.admin.model.ProfitVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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


}
