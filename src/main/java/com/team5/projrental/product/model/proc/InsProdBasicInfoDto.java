package com.team5.projrental.product.model.proc;

import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.product.model.Categories;
import com.team5.projrental.product.model.ProductInsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InsProdBasicInfoDto {

    // useAutoGenKey
    private Integer iproduct;

    private Long iuser;
    private String title;
    private String contents;
    private String addr;
    private String restAddr;
    private String storedPic;


    private Integer price;
    private Integer rentalPrice;
    private Integer deposit;
    private LocalDate buyDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Categories icategory;
    private Double x;
    private Double y;
    private Integer inventory;

    public InsProdBasicInfoDto(Long iuser, String title, String contents, String addr, String restAddr,
                               String storedPic, Integer price, Integer rentalPrice, Integer deposit, LocalDate buyDate,
                               LocalDate rentalStartDate, LocalDate rentalEndDate, Categories icategory, Double x, Double y,
                               Integer inventory) {
        this.iuser = iuser;
        this.title = title;
        this.contents = contents;
        this.addr = addr;
        this.restAddr = restAddr;
        this.storedPic = storedPic;
        this.price = price;
        this.rentalPrice = rentalPrice;
        this.deposit = deposit;
        this.buyDate = buyDate;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.icategory = icategory;
        this.x = x;
        this.y = y;
        this.inventory = inventory;
    }

    public InsProdBasicInfoDto(ProductInsDto dto, String addr, Double x, Double y, Integer inventory) {
        this.iuser = dto.getIuser();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.addr = addr;
        this.restAddr = dto.getRestAddr();
        this.price = dto.getPrice();
        this.rentalPrice = dto.getRentalPrice();
        this.deposit = CommonUtils.getDepositFromPer(dto.getPrice(), dto.getDepositPer());
        this.buyDate = dto.getBuyDate();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.icategory = dto.getIcategory();
        this.x = x;
        this.y = y;
        this.inventory = inventory;
    }
}
