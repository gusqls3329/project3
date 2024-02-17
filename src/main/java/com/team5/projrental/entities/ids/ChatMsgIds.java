package com.team5.projrental.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class ChatMsgIds implements Serializable {

    private Long ichatUser;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long seq;

}
