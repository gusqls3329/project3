package com.team5.projrental.common.security;

import com.team5.projrental.entities.enums.Auth;
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

    public Auth getLoginUserAuth(){
        return Auth.getAuth(getLoginUser().getSecurityPrincipal().getAuth());
    }

    public Map<Integer, Auth> getLoginUserPKAndAuth(){
        return Map.of(getLoginUserPk(), getLoginUserAuth());
    }
}
