package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserFirebaseTokenPatchDto {
    @JsonIgnore
    private Long iuser;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String firebaseToken;
}
