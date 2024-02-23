package com.team5.projrental.user;

import com.team5.projrental.entities.User;
import com.team5.projrental.entities.enums.Auth;
import com.team5.projrental.entities.enums.ProvideType;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.user.model.FindUpwDto;
import com.team5.projrental.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUid (String phone);
}
