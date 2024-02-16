package com.team5.projrental.entities;


import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class BoardLikeIds implements Serializable {
    private Long iuser;
    private Long iboard;


}
