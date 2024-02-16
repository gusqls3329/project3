package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseUser;
import jakarta.persistence.*;

@Entity
public class User extends Users {

    @Embedded
    private BaseUser baseUser;


}
