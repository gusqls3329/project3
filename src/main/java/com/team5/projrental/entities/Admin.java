package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.UsersStatus;
import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends Users {

    private String nick;

    @Enumerated(EnumType.STRING)
    private UsersStatus usersStatus;

}
