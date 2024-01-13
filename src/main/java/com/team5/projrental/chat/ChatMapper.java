package com.team5.projrental.chat;

import com.team5.projrental.chat.model.*;
import com.team5.projrental.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    //-------------------------------- t_chat
    int insChat(ChatInsDto dto); // 채팅 빈 방 생성
    List<ChatSelVo> selChatAll(ChatSelDto dto); // 모든 채팅리스트

    //-------------------------------- t_chat_user
    int insChatUser(ChatUserInsDto dto);
    Integer selChatUserCheck(ChatInsDto dto);

    //-------------------------------- t_chat_msg
    int insChatMsg(ChatMsgInsDto dto);
    List<ChatMsgSelVo> selChatMsgAll(ChatMsgSelDto dto);
    int updChatLastMsgAfterDelByLastMsg(ChatMsgDelDto dto);
    int updChatLastMsg(ChatMsgInsDto dto);

    int chatDelMsg(ChatMsgDelDto dto); // 메세지 삭제

    //상대방의 firebaseToken값 필요. 나의 pic, iuser값 필요
    UserEntity selOtherPersonByLoginUser(ChatMsgInsDto dto);
}
