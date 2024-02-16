package com.team5.projrental.entities;


import com.team5.projrental.entities.ids.PaymentInfoIds;
import com.team5.projrental.entities.mappedsuper.UpdatedAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames ={"ipayment", "iuser"}
        )
})
public class Review extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ireview;



    private Long ipayment;

    private Long iuser;

    @Column(length = 2000)
    private String contents;

    @Column
    private Long raiting;
}
