package com.team5.projrental.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthenticationFacade {

    public SecurityUserDetails getLoginUser() {
        return (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public int getLoginUserPk() {
        return getLoginUser().getSecurityPrincipal().getIuser();
    }

    public int getLoginUserAuth(){
        return getLoginUser().getSecurityPrincipal().getIauth();
    }

    public Map<String, Integer> getLoginUserPKAndAuth(){
        return Map.of("iuser", getLoginUserPk(), "auth", getLoginUserAuth());
    }
}
