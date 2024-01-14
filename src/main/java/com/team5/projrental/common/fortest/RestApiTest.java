package com.team5.projrental.common.fortest;

import com.team5.projrental.common.utils.AxisGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class RestApiTest {

    private final AxisGenerator axisGenerator;

    @GetMapping("/test/rest-api")
    public String test(@RequestParam String query) {
        StringBuilder sb = new StringBuilder();
        Map<String, Double> axis = axisGenerator.getAxis(query);
        Set<String> keys = axis.keySet();
        AtomicInteger flag = new AtomicInteger();
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(0);
        keys.forEach(k -> sb.append(k).append(": ").append(axis.get(k)).append(flag.getAndIncrement() == 0 ? ", " : ""));


        return sb.toString();
    }
}
