package com.team5.projrental.common.security.oauth2;


import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.security.model.SecurityPrincipal;
import com.team5.projrental.common.security.oauth2.userinfo.Oauth2UserInfo;
import com.team5.projrental.common.security.oauth2.userinfo.Oauth2UserInfoFactory;
import com.team5.projrental.entities.enums.Auth;
import com.team5.projrental.user.UserMapper;
import com.team5.projrental.user.model.UserEntity;
import com.team5.projrental.user.model.UserModel;
import com.team5.projrental.user.model.UserSelDto;
import com.team5.projrental.user.model.UserSignupProcDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomeOAuth2UserService extends DefaultOAuth2UserService {
    private final UserMapper mapper;
    private final Oauth2UserInfoFactory factory;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        SocialProviderType socialProviderType = SocialProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        Oauth2UserInfo oauth2UserInfo = factory.getOauth2UserInfo(socialProviderType, user.getAttributes());
        UserSelDto dto = UserSelDto.builder()
                .providerType(socialProviderType.name())
                .uid(oauth2UserInfo.getId()).build();
        UserModel savedUser = mapper.selUserSocial(dto);

        if(savedUser == null){//한번도 로그인한적이 없다면, 회원가입 처리
            savedUser = signupUser(oauth2UserInfo, socialProviderType);
        }

        SecurityPrincipal myPrincipal = SecurityPrincipal.builder()
                .iuser(savedUser.getIuser()).build();
        myPrincipal.setAuth(String.valueOf(savedUser.getAuth()));



        return MyUserDetails.builder()
                .userModel(savedUser)
                .myPrincipal(myPrincipal)
                .attributes(user.getAttributes()).build();

    }

    private UserModel signupUser(Oauth2UserInfo oauth2UserInfo, SocialProviderType socialProviderType){
        UserSignupProcDto dto = new UserSignupProcDto();
        dto.setProviderType(socialProviderType.name());
        dto.setUid(oauth2UserInfo.getId()); //소셜로그인에서 관리하는 pk값(유일값)이 넘어옴
        dto.setUpw("social");
        dto.setNm(oauth2UserInfo.getName());
        dto.setPic(oauth2UserInfo.getImageUrl());
        dto.setAuth(Auth.USER);
        int result = mapper.insUser(dto);

        UserModel entity = new UserModel();
        entity.setUid(dto.getUid());
        entity.setIuser(dto.getIuser());
        entity.setAuth(dto.getAuth());
        entity.setNm(dto.getNm());
        entity.setPic(dto.getPic());
        return entity;
    }
}