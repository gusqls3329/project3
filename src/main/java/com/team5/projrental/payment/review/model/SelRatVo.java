package com.team5.projrental.payment.review.model;

import lombok.Data;

import java.util.List;

@Data
public class SelRatVo {
    private int countIuser;
    private double averageRat;
    private List<Integer> totalRat;
}
