package com.team5.projrental.product.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import com.team5.projrental.product.model.proc.PicsInfoVo;
import com.team5.projrental.product.model.review.ReviewResultVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ProductVo extends ProductListVo{

    // 추가필드만 작성
    private String contents;
    private List<PicsInfoVo> prodSubPics;

    private LocalDate buyDate;
    private Double x;
    private Double y;
//    private Integer isLiked;
    private List<ReviewResultVo> reviews;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LocalDate> disabledDates;

    public ProductVo(GetProductResultDto dto) {
        super(dto);

        this.contents = dto.getContents();
        this.buyDate = dto.getBuyDate();
        this.x = dto.getX();
        this.y = dto.getY();
    }
}
