package com.team5.projrental.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public SecurityUserDetails getLoginUser() {
        return (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public int getLoginUserPk() {
        return getLoginUser().getSecurityPrincipal().getIuser();
    }

}
