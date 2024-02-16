package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatUser {

    @EmbeddedId
    private ChatUserIds chatUserIds;

    @Column(columnDefinition = "BIGINT", nullable = false)
    private Long istatus;
}
