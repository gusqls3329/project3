package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.ProvideType;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseUser;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comp  extends Users {
    @Embedded
    private BaseUser baseUser;

    @Enumerated(EnumType.STRING)
    private ProvideType provideType;

    private Long compCode;
    private String compNm;
    private Long cash;
}
