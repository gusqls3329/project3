package com.team5.projrental.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class BoardPicIds implements Serializable {
    @Column(nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long ipics;
    @Column(nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long iboard;
}
