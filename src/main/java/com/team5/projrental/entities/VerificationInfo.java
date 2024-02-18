package com.team5.projrental.entities;

import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VerificationInfo extends CreatedAt {

    @Id
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(length = 1000)
    private String txId;

}
