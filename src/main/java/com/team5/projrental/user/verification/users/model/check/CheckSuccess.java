package com.team5.projrental.user.verification.users.model.check;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckSuccess {
    private String txId;
    private String status;
    private PersonalData personalData;
}
