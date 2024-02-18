package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.PushCategory;
import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Push {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipush")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iusers")
    private Users users;

    @Enumerated(EnumType.STRING)
    private PushCategory category;
    private String description;

}
