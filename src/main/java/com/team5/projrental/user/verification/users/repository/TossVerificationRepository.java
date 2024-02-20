package com.team5.projrental.user.verification.users.repository;

import com.team5.projrental.entities.VerificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TossVerificationRepository extends JpaRepository<VerificationInfo, String> {

}
