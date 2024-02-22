package com.team5.projrental.common.sse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SseMessageInfo {
    private Long receiver;
    private String description;
    private int code;
    private int identityNum;
    private String type;

}
