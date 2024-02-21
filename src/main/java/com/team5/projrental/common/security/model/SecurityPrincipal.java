package com.team5.projrental.common.security.model;

import com.team5.projrental.entities.enums.Auth;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SecurityPrincipal {
    private Long iuser;
    private String auth;
}
