package com.team5.projrental.user.verification.users.model.ready;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Success {
    private String txId;
    private String appScheme;
    private String iosAppUri;
    private String requestedAt;
    private String authUrl;
}
