package com.team5.projrental.admin.model;

import com.team5.projrental.product.ProductService;
import com.team5.projrental.product.model.Categories;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetProductVo {
    private long iproduc;
    private long iuser;
    private Categories category;
    private Integer price;
    private int view;
    private LocalDateTime createdAt;
    private ProductService status;
}
