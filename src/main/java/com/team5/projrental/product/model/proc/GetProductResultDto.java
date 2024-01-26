package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResultDto extends GetProductListResultDto{
    private String contents;
//    private Integer deposit;
    private LocalDate buyDate;
    private Double x;
    private Double y;
    private Integer isLiked;
    //
    private Integer stockCount;

}
