package com.team5.projrental.user.verification.users.repository;

import com.team5.projrental.entities.VerificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TossVerificationRepository extends JpaRepository<VerificationInfo, Long> {

    Optional<VerificationInfo> findByUserNameAndUserPhoneAndUserBirthday(String userName, String userPhone, String userBirthday);

}
