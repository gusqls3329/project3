package com.team5.projrental.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserVo {
    private long iuser;
    private String uid;
    private String nm;
    private String createdAt;
    private String email;
    private long penalty;
    private long status;
}
