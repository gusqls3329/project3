package com.team5.projrental.common.aop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class DisabledDateInfo {
    private List<LocalDate> disabledDate;
    private Integer y;
    private Integer m;
}
