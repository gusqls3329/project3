package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ResolvedProduct{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iresolved_product")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "iproduct")
    private Product product;

}
