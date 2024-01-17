package com.team5.projrental.product.model.proc;

import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InsProdPicsDto {
    private Integer iproduct;
    private List<String> pics;
}
