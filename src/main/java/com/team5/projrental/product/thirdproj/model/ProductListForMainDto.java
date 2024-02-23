package com.team5.projrental.product.thirdproj.model;

import com.team5.projrental.entities.enums.ProductStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductListForMainDto {
    private Long iuser;
    private String nick;
    private String userPic;


    private Long iproduct;
    private String title;
    private String prodMainPic;
    private Integer rentalPrice;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private String addr;
    private String restAddr;
    private Integer istatus;
    private Long view;
    private EnumCategories enumCategories;
}
