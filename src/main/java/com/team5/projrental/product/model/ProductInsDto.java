package com.team5.projrental.product.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProductInsDto {

    /*
    iuser
title
contents
addr
restAddr
mainPic
pics []
price
rentalPrice
depositPer
buyDate
rentalStartDate
rentalEndDate
category
     */

    private Integer iuser;
    private String title;
    private String contents;
    private String addr;
    private String restAddr;
    private MultipartFile mainPic;
    private List<MultipartFile> pics;
    private Integer price;
    private Integer rentalPrice;
    private Integer depositPer;
    private LocalDate buyDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String category;

}
