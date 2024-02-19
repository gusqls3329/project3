package com.team5.projrental.user.verification.comp.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "api.comp.code")
public class CompCodeProperties {

    private String baseUrl;
    private String serviceCodeKey;
    private String serviceCodeValue;

}
