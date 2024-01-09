package com.team5.projrental.product.model;


import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class CurProductListVo extends ProductListVo{

    // 추가필드만 작성
    private Integer iuser;
    private String contents;
    private List<PicSet> prodPics;
    private Integer deposit;
    private LocalDate buyDate;
    private Boolean x;
    private Boolean y;

    public CurProductListVo(GetProductResultDto dto) {
        super(dto);
        this.iuser = dto.getIuser();
        this.contents = dto.getContents();
        this.deposit = dto.getDeposit();
        this.buyDate = dto.getBuyDate();
        this.x = dto.getX();
        this.y = dto.getY();
    }
}
