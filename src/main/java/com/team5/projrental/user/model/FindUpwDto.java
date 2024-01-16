package com.team5.projrental.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class FindUpwDto {
    @NotBlank
    @Pattern(regexp = "(01)\\d-\\d{3,4}-\\d{4}")
    private String phone;

    @NotBlank
    @Length(min = 8, max = 15)
    private String uid;

    @NotBlank
    @Length(min = 8, max = 20)
    private String upw;
}
