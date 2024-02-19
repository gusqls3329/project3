package com.team5.projrental.websocket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    private String roomId;
    private String writer;
    private String message;

}
