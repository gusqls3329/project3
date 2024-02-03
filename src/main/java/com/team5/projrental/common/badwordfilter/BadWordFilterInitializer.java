package com.team5.projrental.common.badwordfilter;

import com.team5.projrental.common.utils.CommonUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BadWordFilterInitializer {
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        CommonUtils.filterWord = new ArrayList<>();
        CommonUtils.filterWord.add("게이밍");
        CommonUtils.filterWord.add("니 미");

    }
}
