package com.team5.projrental.user;

import com.team5.projrental.entities.inheritance.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
