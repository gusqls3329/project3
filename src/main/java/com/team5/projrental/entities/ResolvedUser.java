package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ResolvedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySql에서 mariaDb에서 사용
    @Column(name = "iresolved_user")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "iusers")
    private Users users;

}
