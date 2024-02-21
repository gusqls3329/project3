package com.team5.projrental.product.thirdproj.japrepositories;

import com.team5.projrental.entities.ProdLike;
import com.team5.projrental.entities.ids.ProdLikeIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdLikeJpaRepository extends JpaRepository<ProdLike, ProdLikeIds> {
    Integer countByProdLikeIds(ProdLikeIds ids);
}
