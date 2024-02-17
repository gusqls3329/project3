package com.team5.projrental.entities.mappedsuper;

import com.team5.projrental.entities.embeddable.Address;
import com.team5.projrental.entities.enums.UsersStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BaseUser  {

    @Embedded
    private Address address;
    private String storedPic;
    private Boolean rating;
    private String verification;
    @Enumerated(EnumType.STRING)
    private UsersStatus status;
}
