package com.team5.projrental.product.thirdproj.japrepositories.product;

import com.team5.projrental.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {

    @Query("select p from Product p join fetch p.stocks where p.id in (:iproducts)")
    List<Product> findByIdIn(List<Long> iproducts);
}
