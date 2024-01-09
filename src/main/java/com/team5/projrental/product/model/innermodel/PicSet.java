package com.team5.projrental.product.model.innermodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicSet {

    private Resource prodPic;
    private Integer ipics;
}
