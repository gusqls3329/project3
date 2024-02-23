package com.team5.projrental.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class GetDisputeListVo {
    private long totalDisputeCount;
    List<GetDisputeVo> getDisputeVos;
}
