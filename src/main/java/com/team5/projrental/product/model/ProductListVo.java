package com.team5.projrental.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListVo {

    private Long iuser;
    private String nick;
    private String userPic;
    @JsonInclude(JsonInclude.Include.NON_NULL) // FIXME 3차 완성시 삭제 (제외 필드)
    private Integer iauth;
    private Long iproduct;
    private String title;
    private String prodMainPic;
    private Integer rentalPrice;
    //@JsonInclude(JsonInclude.Include.NON_NULL) // FIXME 3차 완성시 삭제 (제외 필드)
    //private Integer deposit;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private String addr;
    private String restAddr;
    private Long view;
    private Integer istatus;
    // prod_like
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer prodLike;

    // stock
    private Integer inventory; // 해당 제품의 총 재고 (다 나간거 관계 없이)
    private Integer isLiked;
    // enum 객체 따로존재
    private Categories categories;


    public ProductListVo(GetProductListResultDto dto) {
        this.iuser = dto.getIuser();
        this.nick = dto.getNick();
        this.iauth = dto.getIauth();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        //this.deposit = dto.getDeposit();
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
       // this.deposit = dto.getDeposit();
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
