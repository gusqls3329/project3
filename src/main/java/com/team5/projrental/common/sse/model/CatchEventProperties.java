package com.team5.projrental.common.sse.model;

import com.team5.projrental.common.sse.responseproperties.Properties;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CatchEventProperties {
    private Integer iuser;
    private String sseEmitterName;
    private Properties pushInfo;
    private Integer addedCode;
}
