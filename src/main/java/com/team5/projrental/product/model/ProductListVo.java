package com.team5.projrental.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductListVo {

    private Integer iuser;
    private String nick;
    private String userPic;
    private Integer iauth;
    private Integer iproduct;
    private String title;
    private String prodMainPic;
    private Integer price;
    private Integer rentalPrice;
    private Integer deposit;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;
    private String restAddr;
    private Integer prodLike;
    private Integer istatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer inventory;
    private Integer isLiked;
    private Integer view;
    private Categories categories;

    public ProductListVo(GetProductListResultDto dto) {
        this.iuser = dto.getIuser();
        this.nick = dto.getNick();
        this.iauth = dto.getIauth();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.price = dto.getPrice();
        this.deposit = dto.getDeposit();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
        this.restAddr = dto.getRestAddr();
        this.prodLike = dto.getProdLike();
        this.userPic = dto.getUserStoredPic();
        this.prodMainPic = dto.getProdMainStoredPic();
        this.istatus = dto.getIstatus();
        this.view = dto.getView();
        this.isLiked = dto.getIsLiked();
        this.categories = dto.getIcategory();
    }

    public ProductListVo(GetProductResultDto dto) {
        this.iuser = dto.getIuser();
        this.nick = dto.getNick();
        this.iauth = dto.getIauth();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.price = dto.getPrice();
        this.deposit = dto.getDeposit();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
        this.restAddr = dto.getRestAddr();
        this.prodLike = dto.getProdLike();
        this.userPic = dto.getUserStoredPic();
        this.prodMainPic = dto.getProdMainStoredPic();
        this.istatus = dto.getIstatus();
        this.inventory = dto.getInventory();
        this.view = dto.getView();
        this.isLiked = dto.getIsLiked();
        this.categories = dto.getIcategory();
    }

}
