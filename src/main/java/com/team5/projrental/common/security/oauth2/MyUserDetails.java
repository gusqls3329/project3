package com.team5.projrental.common.security.oauth2;

import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.security.model.SecurityPrincipal;
import com.team5.projrental.user.model.UserModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class MyUserDetails implements UserDetails, OAuth2User {

    private SecurityPrincipal myPrincipal;
    private Map<String, Object> attributes;
    private UserModel userModel; //

    @Override //권한이 무엇이있는지에 대한 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(myPrincipal == null){
            return null;
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.myPrincipal.getAuth()));
    }

    @Override //1. 루틴(여기에 값이 리턴하도록_아이디가 리턴되도록..) 2. 커스터마이징(직접응답, 리턴까지 직접구현)
    public String getPassword() {
        return null;
    }

    @Override//1. 루틴(여기에 값이 리턴하도록_비번이 리턴되도록..) 2. 커스터마이징(직접응답, 리턴까지 직접구현)
    public String getUsername() {
        return userModel == null ? null : userModel.getUid();
    }

    @Override //Account가Expired가 안되었나
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override//Account가 Locked이 안되었나
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //활성화 true, 비활성화 : false
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
