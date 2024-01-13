package com.team5.projrental.common.utils;

import com.team5.projrental.common.aop.anno.Retry;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AxisGenerator {

    @Retry // default value = 3 (3 retry)
    public Map<String, Double> getAxis(String fullAddr) {
        return CommonUtils.getAxis(fullAddr);
    }

}
