package com.team5.projrental.admin;

import com.team5.projrental.entities.Admin;
import com.team5.projrental.entities.inheritance.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Users>, ProfitQdslRepository{
    
}
