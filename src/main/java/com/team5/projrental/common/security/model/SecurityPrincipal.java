package com.team5.projrental.common.security.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SecurityPrincipal {
    private int iuser;
    private int iauth;
}
