package com.team5.projrental.entities.mappedsuper;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class UpdatedAt {

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
