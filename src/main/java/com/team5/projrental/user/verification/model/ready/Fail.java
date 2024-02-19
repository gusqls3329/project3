package com.team5.projrental.user.verification.model.ready;

import lombok.Getter;

@Getter
public class Fail {
    private String resultType;
    private Success success;
    private Fail fail;
}
