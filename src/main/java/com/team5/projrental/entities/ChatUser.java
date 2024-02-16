package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_chat_user")
public class ChatUser {

    @EmbeddedId
    private ChatUserIds chatUserIds;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(columnDefinition = "BIGINT UNSIGNED", name = "iuser")
    private User user;

    @ManyToOne
    @MapsId("ichat")
    @JoinColumn(columnDefinition = "BIGINT UNSIGNED", name = "ichat")
    private Chat chat;

    @Column(columnDefinition = "BIGINT")
    private Long istatus;
}
