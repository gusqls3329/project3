package com.team5.projrental.product.thirdproj.japrepositories.product;

import com.team5.projrental.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {
}
