package com.team5.projrental.product.model.proc;

import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Data
public class GetProductResultDto {


    private String nick;
    private String userPic;

    private Integer iproduct;
    private String title;
    private String prodPic;
    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;
    private Integer like;
}
