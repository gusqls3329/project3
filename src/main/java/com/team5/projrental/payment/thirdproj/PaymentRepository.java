package com.team5.projrental.payment.thirdproj;

import com.team5.projrental.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> , PaymentQueryRepository{
}
