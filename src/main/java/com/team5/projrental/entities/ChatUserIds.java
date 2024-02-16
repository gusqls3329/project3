package com.team5.projrental.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Embeddable
@EqualsAndHashCode
public class ChatUserIds {

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ichat;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long seq;
}
