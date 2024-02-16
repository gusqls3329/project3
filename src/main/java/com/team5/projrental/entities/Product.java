package com.team5.projrental.entities;

import com.team5.projrental.entities.embeddable.Address;
import com.team5.projrental.entities.embeddable.RentalDates;
import com.team5.projrental.entities.enums.ProductCategory;
import com.team5.projrental.entities.enums.ProductStatus;
import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iproduct;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String title;
    private String contents;
    private String storedPic;
    private String rental_price;

    @Embedded
    private RentalDates rentalDates;
    private Long view;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;





}
