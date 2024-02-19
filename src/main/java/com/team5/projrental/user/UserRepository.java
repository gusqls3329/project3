package com.team5.projrental.user;

import com.team5.projrental.entities.User;
import com.team5.projrental.entities.enums.ProvideType;
import com.team5.projrental.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProvideTypeAndUid(ProvideType provideType, String uid);
}
