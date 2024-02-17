package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.ChatUserStatus;
import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ichatUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ichat")
    private Chat chat;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "iusers")
    private Users users;

    @Enumerated(EnumType.STRING)
    private ChatUserStatus status;
}
