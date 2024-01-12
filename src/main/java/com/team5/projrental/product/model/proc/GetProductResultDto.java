package com.team5.projrental.product.model.proc;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResultDto extends GetProductListResultDto{
    private Integer iuser;
    private String contents;
    private Integer deposit;
    private LocalDate buyDate;
    private Double x;
    private Double y;

}
