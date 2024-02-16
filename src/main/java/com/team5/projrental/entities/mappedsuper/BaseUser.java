package com.team5.projrental.entities.mappedsuper;

import com.team5.projrental.entities.embeddable.Address;
import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BaseUser  {

    @Embedded
    private Address address;
    private String nick;
    private String storedPic;
    private Boolean rating;
    private String verification;
    private Long compCode;
    private String compNm;
    private Integer cash;

}
