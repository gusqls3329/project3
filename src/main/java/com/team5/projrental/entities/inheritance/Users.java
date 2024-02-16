package com.team5.projrental.entities.inheritance;

import com.team5.projrental.entities.enums.Auth;
import com.team5.projrental.entities.enums.UsersStatus;
import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn
public class Users extends BaseAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iuser;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    @Column(unique = true)
    private String uid;
    private String upw;
    private String phone;
    private String email;
}
