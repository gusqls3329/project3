package com.team5.projrental.product.thirdproj.japrepositories;

import com.team5.projrental.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
