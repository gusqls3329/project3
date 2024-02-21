package com.team5.projrental.admin.model;

import lombok.Data;

@Data
public class ProfitVo {
    private int iproduct;
    private String title;
    private String mainPic;
    private Integer exposureCount;
    private Integer profit;
    private Integer viewOfMonth;
    private Integer beforeView;
    private Integer finalView;
    private String fromDate;
    private String toDate;
}
