package com.team5.projrental.product.model;

import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductListVo {

    private Integer iuser;
    private String nick;
    private String userPic;

    private Integer iproduct;
    private String title;
    private String prodPic;
    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;
    private Integer prodLike;
    private Integer istatus;

    public ProductListVo(GetProductListResultDto dto) {
        this.iuser = dto.getIuser();
        this.nick = dto.getNick();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
        this.prodLike = dto.getProdLike();
        this.userPic = dto.getUserStoredPic();
        this.prodPic = dto.getProdMainStoredPic();
        this.istatus = dto.getIstatus();
    }

    public ProductListVo(GetProductResultDto dto) {
        this.iuser = dto.getIuser();
        this.nick = dto.getNick();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
        this.prodLike = dto.getProdLike();
        this.istatus = dto.getIstatus();
    }

}
