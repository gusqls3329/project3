package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.ProvideType;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends Users {

    @Embedded
    private BaseUser baseUser;

    private String nick;

    @Enumerated(EnumType.STRING)
    private ProvideType provideType;

}
