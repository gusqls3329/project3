package com.team5.projrental.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListVo {

    /*
    nick: ,
  userPic: ,

  iproduct: ,
  title: ,
  prodPic:,
  rentalPrice: ,
  rentalStartDate
  rentalEndDate
  addr (rest_addr 까지 포함)
     */

    private String nick;
    private ResponseEntity<Resource> userPic;

    private Integer iproduct;
    private String title;
    private ResponseEntity<Resource> prodPic;
    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;

}
