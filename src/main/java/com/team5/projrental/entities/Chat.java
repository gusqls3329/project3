package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ichat;

    private Long iproduct;
    private String lastMsg;
    //lastMsgAt
    //createdAt
}
