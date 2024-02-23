package com.team5.projrental.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class GetProductListVo {
    private long totalDisputeCount;
    List<GetProductVo> getProductVos;
}
