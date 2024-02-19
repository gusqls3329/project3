package com.team5.projrental.common.security.oauth2.userinfo;

import com.team5.projrental.common.security.oauth2.SocialProviderType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.team5.projrental.common.security.oauth2.SocialProviderType.KAKAO;
import static com.team5.projrental.common.security.oauth2.SocialProviderType.NAVER;

@Component
public class Oauth2UserInfoFactory {
    public Oauth2UserInfo getOauth2UserInfo(SocialProviderType socialProviderType,
                                            Map<String, Object> attrs){

        return switch(socialProviderType){
            case KAKAO -> new KakaoOAuth2UserInfo(attrs);
            case NAVER -> new NaverOAuth2UserInfo(attrs);
            default -> throw new IllegalArgumentException("Invalid Provider Type.");
        };

    }

}
