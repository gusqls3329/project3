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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"uid", "provider_type"})})

public class User extends Users {

    @Embedded
    private BaseUser baseUser;

    @Enumerated(EnumType.STRING)
    private ProvideType provideType;

}
