package com.team5.projrental.entities.mappedsuper;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseAt extends CreatedAt{

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
