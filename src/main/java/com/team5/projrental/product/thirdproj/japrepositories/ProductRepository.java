package com.team5.projrental.product.thirdproj.japrepositories;

import com.team5.projrental.entities.enums.ProductMainCategory;
import com.team5.projrental.entities.enums.ProductSubCategory;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.jpa.ActivatedStock;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ProductRepository {
    Map<Long, List<ActivatedStock>> getActivatedStock(LocalDate refDate);

    List<ProductListVo> findAllBy(Integer sort,
                                  String search,
                                  ProductMainCategory mainCategory,
                                  ProductSubCategory subCategory,
                                  int page,
                                  Long iuser,
                                  int prodPerPage);

    List<ProductListVo> findProductListVoByIproducts(List<Integer> imainCategory,
                                                     List<Integer> isubCategory);
}
