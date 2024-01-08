package com.team5.projrental.product.model;

import com.team5.projrental.product.model.proc.GetProductResultDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProductListVo {


    private String nick;
    private ResponseEntity<Resource> userPic;

    private Integer iproduct;
    private String title;
    private ResponseEntity<Resource> prodPic;
    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;

    public ProductListVo(GetProductResultDto dto) {
        this.nick = dto.getNick();
        this.iproduct = dto.getIproduct();
        this.title = dto.getTitle();
        this.rentalPrice = dto.getRentalPrice();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.addr = dto.getAddr();
    }

}
