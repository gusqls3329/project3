package com.team5.projrental.product.model;

import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductListVo {


    private String nick;
    private Resource userPic;

    private Integer iproduct;
    private String title;
    private Resource prodPic;
    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;
    private Integer prodLike;

    public ProductListVo(GetProductListResultDto dto) {
        this.nick = dto.getNick();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
        this.prodLike = dto.getProdLike();
    }

    public ProductListVo(GetProductResultDto dto) {
        this.nick = dto.getNick();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
        this.prodLike = dto.getProdLike();
    }

}