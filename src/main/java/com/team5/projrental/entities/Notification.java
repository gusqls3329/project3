package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notification extends BaseAt {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inoti;

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;

    private String title;
    private String contents;

}
