package com.team5.projrental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_chat_user")
public class ChatUser {
    private Long ichat;
    private Long iuser;
    private Long istatus;
}
