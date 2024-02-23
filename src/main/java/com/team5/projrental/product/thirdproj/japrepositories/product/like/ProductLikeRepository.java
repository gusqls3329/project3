package com.team5.projrental.product.thirdproj.japrepositories.product.like;

import com.team5.projrental.entities.ProdLike;
import com.team5.projrental.entities.Product;
import com.team5.projrental.entities.User;
import com.team5.projrental.entities.ids.ProdLikeIds;
import com.team5.projrental.product.thirdproj.model.ProductLikeCountAndMyLikeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikeRepository extends JpaRepository<ProdLike, ProdLikeIds>, ProductLikeQueryRepository {
    Integer countByProdLikeIds(ProdLikeIds ids);

}
