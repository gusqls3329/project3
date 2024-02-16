package com.team5.projrental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_chat_msg")
public class ChatMsg {

    private Long ichat;
    private Long iuser;
    private Long seq;
    private String msg;
    //createdAt
}
