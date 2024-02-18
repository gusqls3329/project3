package com.team5.projrental.product.thirdproj.japrepositories;

import com.team5.projrental.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
