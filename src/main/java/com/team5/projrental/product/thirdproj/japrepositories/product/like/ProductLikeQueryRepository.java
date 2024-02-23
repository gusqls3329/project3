package com.team5.projrental.product.thirdproj.japrepositories.product.like;

import com.team5.projrental.entities.ProdLike;
import com.team5.projrental.entities.Product;
import com.team5.projrental.entities.User;
import com.team5.projrental.entities.ids.ProdLikeIds;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.thirdproj.model.IsLikedDto;
import com.team5.projrental.product.thirdproj.model.ProductLikeCountAndMyLikeDto;

import java.util.List;

public interface ProductLikeQueryRepository {

    ProductLikeCountAndMyLikeDto findLikeCountAndIsLikedBy(Product product, User user);


    List<ProdLike> countByIuserAndInIproduct(Long iuser, List<Long> iproducts);

}
