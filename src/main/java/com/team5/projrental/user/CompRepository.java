package com.team5.projrental.user;

import com.team5.projrental.entities.Comp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompRepository extends JpaRepository<Comp, Long> {
}
