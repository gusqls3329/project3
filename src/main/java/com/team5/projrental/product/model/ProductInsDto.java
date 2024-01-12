package com.team5.projrental.product.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInsDto {
    @NotNull
    private Integer iuser;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String addr;
    @NotBlank
    private String restAddr;
    @NotNull
    private MultipartFile mainPic;
    private List<MultipartFile> pics;
    @NotNull
    @Range(min = 100, max = Integer.MAX_VALUE)
    private Integer price;
    @NotNull
    @Range(min = 100, max = Integer.MAX_VALUE)
    private Integer rentalPrice;
    @NotNull
    @Range(min = 50, max = 100)
    private Integer depositPer;
    private LocalDate buyDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    @NotBlank
    private String category;

}
