package com.team5.projrental.entities;

import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ChatMsg extends CreatedAt {

    @EmbeddedId
    private ChatMsgIds chatMsgIds;

    @Column(length = 2000, nullable = false)
    private String msg;
    //createdAt
}
