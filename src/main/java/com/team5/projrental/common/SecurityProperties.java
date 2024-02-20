package com.team5.projrental.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ConfigurationProperties(prefix = "app")
public class SecurityProperties {
    private final Jwt jwt = new Jwt();
    private final Oauth2 oauth2 = new Oauth2();

    @Getter
    @Setter
    public class Jwt {
        private String secret;
        private String headerSchemeName;
        private String tokenType;
        private Long accessTokenExpiry;
        private Long refreshTokenExpiry;
        private int refreshTokenCookieMaxAge;

        public void setRefreshTokenExpiry(Long refreshTokenExpiry) {
            this.refreshTokenExpiry = refreshTokenExpiry;
            this.refreshTokenCookieMaxAge = (int) (refreshTokenExpiry * 0.001);
        }

    }

    @Getter
    public static final class Oauth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();
    }
}

