package com.team5.projrental.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetUsersListVo {
    private long totalUserCount;
    List<GetUserVo> getUserVos;
}
