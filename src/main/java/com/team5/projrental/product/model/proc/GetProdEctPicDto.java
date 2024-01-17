package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProdEctPicDto {
    private Integer ipics;
    private String prodStoredPic;
}
