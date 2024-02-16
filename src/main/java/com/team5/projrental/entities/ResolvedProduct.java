package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ResolvedProduct{

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Users users;

}
