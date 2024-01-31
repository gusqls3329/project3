package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdProdBasicDto {
    //    private Categories icategory;
//    private String addr;
//    private String restAddr;
//    private String title;
//    private String contents;
//    private StoredFileInfo mainPic;
    private String storedPic;
    private Integer price;
//    private Integer rentalPrice;
    private Integer deposit;
    private LocalDate buyDate;
//    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;

    //
    private Integer depositPer;

    public UpdProdBasicDto(Integer price, Integer deposit, LocalDate buyDate, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.price = price;
        this.deposit = deposit;
        this.buyDate = buyDate;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }
}
