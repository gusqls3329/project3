package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.PushCategory;
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
    private Long ipush;

    @ManyToOne
    @JoinColumn(name = "iuser")
    private User user;

    private PushCategory category;
    private String description;

}
