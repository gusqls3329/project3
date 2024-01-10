package com.team5.projrental.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    private Integer iproduct;
    private Integer iuser;
    private Integer icategory;
    private Integer iaddr;
    private String restAddr;
    private String title;
    private String contents;
    private String storedPic;
    private String requestPic;
    private Integer price;
    private Integer rentalPrice;
    private Integer deposit;
    private LocalDate buyDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer view;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double x;
    private Double y;


}
