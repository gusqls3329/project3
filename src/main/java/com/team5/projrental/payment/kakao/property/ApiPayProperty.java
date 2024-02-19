package com.team5.projrental.payment.kakao.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "api.pay.kakao")
public class ApiPayProperty {
    private String cid;
    private String secretKey;
    private String headerKey;
    private String headerValue;

    private String successRedirectUrl;
    private String cancelRedirectUrl;
    private String failRedirectUrl;
    private String requestUrl;
    private String approveUrl;

}
