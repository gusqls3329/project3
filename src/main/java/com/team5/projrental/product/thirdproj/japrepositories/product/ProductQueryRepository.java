package com.team5.projrental.product.thirdproj.japrepositories.product;

import com.team5.projrental.entities.Product;
import com.team5.projrental.entities.User;
import com.team5.projrental.entities.enums.ProductMainCategory;
import com.team5.projrental.entities.enums.ProductSubCategory;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.jpa.ActivatedStock;
import com.team5.projrental.product.thirdproj.model.ProductLikeCountAndMyLikeDto;
import com.team5.projrental.product.thirdproj.model.ProductListForMainDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ProductQueryRepository {
    Map<Long, List<ActivatedStock>> getActivatedStock(LocalDateTime refDate);

    List<ProductListVo> findAllBy(Integer sort,
                                  String search,
                                  ProductMainCategory mainCategory,
                                  ProductSubCategory subCategory,
                                  int page,
                                  Long iuser,
                                  int prodPerPage);


    List<ProductListForMainDto> findEachTop8ByCategoriesOrderByIproductDesc(int limitNum);



}
