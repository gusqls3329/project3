package com.team5.projrental.user.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SigninDto {
    @NotBlank
    @Length(min = 8, max = 15)
    private String uid;

    @NotBlank
    @Length(min = 8, max = 20)
    private String upw;
}
