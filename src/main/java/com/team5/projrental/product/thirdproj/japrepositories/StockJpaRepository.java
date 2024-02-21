package com.team5.projrental.product.thirdproj.japrepositories;

import com.team5.projrental.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepository extends JpaRepository<Stock, Long> {

    int countById(Long id);
}
