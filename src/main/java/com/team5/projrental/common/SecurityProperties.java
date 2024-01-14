package com.team5.projrental.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app")
public class SecurityProperties {
    private final Jwt jwt = new Jwt();

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
}
