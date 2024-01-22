package com.team5.projrental.common.fortest;

import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class RestApiTest {

    private final KakaoAxisGenerator axisGenerator;

    @Operation(hidden = true)
    @GetMapping("/test/rest-api")
    public String test(@RequestParam String query) {
        StringBuilder sb = new StringBuilder();
        Addrs addrs = axisGenerator.getAxis(query);

        AtomicInteger flag = new AtomicInteger();
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(0);
        sb.append("addressName: ").append(addrs.getAddress_name())
                .append("x: ").append(addrs.getX()).append("y: ").append(addrs.getY());


        return sb.toString();
    }
}
