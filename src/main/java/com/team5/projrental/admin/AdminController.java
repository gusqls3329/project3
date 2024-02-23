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


}
