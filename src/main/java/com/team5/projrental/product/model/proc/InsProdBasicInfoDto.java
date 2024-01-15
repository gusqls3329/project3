package com.team5.projrental.product.model.proc;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.IllegalCategoryException;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.product.model.ProductInsDto;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
public class InsProdBasicInfoDto {

    // useAutoGenKey
    private Integer iproduct;

    private Integer iuser;
    private String title;
    private String contents;
    private Integer iaddr;
    private String restAddr;
    private StoredFileInfo mainPicObj; // #{mainPicObj.requestPic} #{mainPicObj.storedPic}
    // 따로 setter 사용해야함.

    private Integer price;
    private Integer rentalPrice;
    private Integer deposit;
    private LocalDate buyDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer icategory;
    private Double x;
    private Double y;

    public InsProdBasicInfoDto(Integer iuser, String title, String contents, Integer iaddr, String restAddr, StoredFileInfo mainPicObj, Integer price, Integer rentalPrice, Integer deposit, LocalDate buyDate, LocalDate rentalStartDate, LocalDate rentalEndDate, Integer icategory, Double x, Double y) {
        this.iuser = iuser;
        this.title = title;
        this.contents = contents;
        this.iaddr = iaddr;
        this.restAddr = restAddr;
        this.mainPicObj = mainPicObj;
        this.price = price;
        this.rentalPrice = rentalPrice;
        this.deposit = deposit;
        this.buyDate = buyDate;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.icategory = icategory;
        this.x = x;
        this.y = y;
    }

    public InsProdBasicInfoDto(ProductInsDto dto, Integer iaddr, Double x, Double y) {
        this.iuser = dto.getIuser();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.iaddr = iaddr;
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
    }

    private Integer parseCategory(String category) {
        Map<Integer, String> categories = Const.CATEGORIES;
        return categories
                .keySet()
                .stream()
                .filter(k -> categories.get(k).equals(category))
                .findFirst()
                .orElseThrow(() -> new IllegalCategoryException(Const.ILLEGAL_CATEGORY_EX_MESSAGE));
    }
}
