package com.team5.projrental.entities;

import com.team5.projrental.entities.embeddable.Address;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iproduct;

    @Embedded
    private Address address;



}
