package com.team5.projrental.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class ChatUserIds implements Serializable {

    private Long ichat;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iuser;
}
