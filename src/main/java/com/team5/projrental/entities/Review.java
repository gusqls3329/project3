package com.team5.projrental.entities;


import com.team5.projrental.entities.ids.PaymentInfoIds;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.UpdatedAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames ={"ipayment", "iusers"}
        )
})
public class Review extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ireview")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ipayment")
    private Payment payment;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iusers")
    private Users users;


    @Column(length = 2000)
    private String contents;

    private Long rating;
}
