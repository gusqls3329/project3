package com.team5.projrental.product.model.proc;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.IllegalCategoryException;
import com.team5.projrental.product.model.ProductInsDto;
import com.team5.projrental.product.model.innermodel.PicSet;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class InsProdBasicInfoDto {

    // useAutoGenKey
    private Integer iproduct;

    private Integer iuser;
    private String title;
    private String contents;
    private String addr;
    private String restAddr;
    private StoredFileInfo mainPicObj; // #{mainPicObj.requestPic} #{mainPicObj.storedPic}
    // todo 따로 setter 사용해야함.

    private Integer price;
    private Integer rentalPrice;
    private Integer depositPer;
    private LocalDate buyDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer icategory;
    private Double x;
    private Double y;

    public InsProdBasicInfoDto(ProductInsDto dto, Double x, Double y) {
        this.iuser = dto.getIuser();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.addr = dto.getAddr();
        this.restAddr = dto.getRestAddr();
        this.price = dto.getPrice();
        this.rentalPrice = dto.getRentalPrice();
        this.depositPer = (int) (dto.getPrice() * depositPer * 0.01);
        this.buyDate = dto.getBuyDate();
        this.rentalStartDate = dto.getRentalStartDate();
        this.rentalEndDate = dto.getRentalEndDate();
        this.icategory = parseCategory(dto.getCategory());
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
