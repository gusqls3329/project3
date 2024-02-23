package com.team5.projrental.entities;

import com.team5.projrental.entities.embeddable.Address;
import com.team5.projrental.entities.embeddable.RentalDates;
import com.team5.projrental.entities.enums.ProductMainCategory;
import com.team5.projrental.entities.enums.ProductStatus;
import com.team5.projrental.entities.enums.ProductSubCategory;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iproduct")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iuser")
    private User user;

    @Enumerated(EnumType.STRING)
    private ProductMainCategory mainCategory;
    @Enumerated(EnumType.STRING)
    private ProductSubCategory subCategory;

    @Embedded
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ProdLike> prodLikes = new ArrayList<>();

    private String title;
    private String contents;
    private String storedPic;
    private Integer rentalPrice;

    @Embedded
    private RentalDates rentalDates;
    private Long view;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;


    @Builder.Default
    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks = new ArrayList<>();



}
