package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VerificationInfo {

    @Id
    @Column(unique = true)
    private String uuid;

    @Column(length = 1000)
    private String txId;

}
