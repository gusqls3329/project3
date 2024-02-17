package com.team5.projrental.entities;

import com.team5.projrental.entities.ids.ChatMsgIds;
import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatMsg extends CreatedAt {

    @EmbeddedId
    private ChatMsgIds chatMsgIds;

    @MapsId("ichatUser")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ichat_user")
    private ChatUser chatUser;


    @Column(length = 2000, nullable = false)
    private String msg;
}
