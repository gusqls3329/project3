package com.team5.projrental.entities.ids;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class HashTagIds implements Serializable {

    private Long iproduct;
    private String tag;

}
