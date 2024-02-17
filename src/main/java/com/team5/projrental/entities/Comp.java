package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.JoinStatus;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comp  extends Users {
    @Embedded
    private BaseUser baseUser;

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;
    @Column(unique = true)
    private String nick;
    private Long compCode;
    private String compNm;
    private Long cash;
    @Enumerated(EnumType.STRING)
    private JoinStatus joinStatus;
}
