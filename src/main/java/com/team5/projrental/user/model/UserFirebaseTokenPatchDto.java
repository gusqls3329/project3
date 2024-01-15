package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserFirebaseTokenPatchDto {
    @JsonIgnore
    private int iuser;
    private String firebaseToken;
}
