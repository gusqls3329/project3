package com.team5.projrental.chat;

import com.team5.projrental.chat.model.*;
import com.team5.projrental.user.model.UserEntity;
import com.team5.projrental.user.model.UserSelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {

    int insChat(ChatInsDto dto); // 채팅 빈 방 생성
    List<ChatSelVo> selChatAll(ChatSelDto dto); // 채팅리스트

    int insChatUser(ChatUserInsDto dto); // 빈 채팅방에 유저 참여
    List<ChatUserInsDto> insChatUserTest(ChatUserInsDto dto); // TDD 용

    //Integer selChatUserCheck(ChatInsDto dto);
    Integer selChatUserCheck2(ChatInsDto dto);

    int insChatMsg(ChatMsgInsDto dto);
    List<ChatMsgSelVo> selChatMsgAll(ChatMsgSelDto dto);
    int updChatLastMsgAfterDelByLastMsg(ChatMsgDelDto dto);
    int updChatLastMsg(ChatMsgInsDto dto);

    // int chatDelMsg(ChatMsgDelDto dto); // 메세지 삭제

    //상대방의 firebaseToken값 필요. 나의 pic, iuser값 필요
    UserEntity selOtherPersonByLoginUser(ChatMsgInsDto dto);

    UserEntity selChatUser(Long iuser);

    int delBeforeChatIstatus(ChatMsgInsDto dto);
}
