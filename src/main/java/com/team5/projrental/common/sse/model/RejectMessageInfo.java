package com.team5.projrental.common.sse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectMessageInfo {
    private int iuser;
    private String message;
    private int code;
    private int num;
    private String name;

}
