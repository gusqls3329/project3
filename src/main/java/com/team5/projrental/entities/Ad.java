package com.team5.projrental.entities;

import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ad extends BaseAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iad;

    @ManyToOne
    @JoinColumn(name = "icomp")
    private Comp comp;

    @ManyToOne
    @JoinColumn(name = "iproduct")
    private Product product;

    private Long beforeView;
    private Long finalView;
    private Integer viewOfMonth;
    private Long cash;
    private Integer balance;
    private Integer exposureCount;
    private Integer profit;
    private LocalDateTime calculatedRef;

}
