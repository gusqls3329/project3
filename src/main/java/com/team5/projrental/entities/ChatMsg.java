package com.team5.projrental.entities;

import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_chat_msg")
public class ChatMsg extends CreatedAt {

    @EmbeddedId
    private ChatMsgIds chatMsgIds;

    @ManyToOne
    @MapsId("ichat")
    @JoinColumn(columnDefinition = "BIGINT UNSIGNED", name = "ichat")
    private Chat chat;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(columnDefinition = "BIGINT UNSIGNED", name = "iuser")
    private User user;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long seq;

    @Column(length = 2000, nullable = false)
    private String msg;
    //createdAt
}
