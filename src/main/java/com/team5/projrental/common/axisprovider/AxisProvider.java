package com.team5.projrental.common.axisprovider;

import com.team5.projrental.common.aop.anno.Retry;
import com.team5.projrental.common.utils.CommonUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AxisProvider {

    @Retry
    public Map<String, Double> getAxis(String fullAddr) {
        return CommonUtils.getAxis(fullAddr);
    }

}
