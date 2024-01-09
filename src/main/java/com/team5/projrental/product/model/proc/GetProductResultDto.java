package com.team5.projrental.product.model.proc;

import com.team5.projrental.product.model.StoredFileInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetProductResultDto {


    private String nick;
    private String userPic;

    private Integer iproduct;
    private String title;
    private String requestPic;
    private StoredFileInfo storedFileInfo;
    private Integer rentalPrice;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private String addr;
    private Integer like;
}
