package com.team5.projrental.entities;

import com.team5.projrental.entities.embeddable.Address;
import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TradeLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itradeLocation;

    @OneToOne
    @JoinColumn(name = "iusers")
    private Users users;

    @Embedded
    private Address address;

}
