package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class Admin {



    private String nick;
}
