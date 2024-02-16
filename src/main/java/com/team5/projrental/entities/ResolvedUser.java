package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ResolvedUser{
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySql에서 mariaDb에서 사용
    private Long iuser;
    //iresolved_user
    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;

}
