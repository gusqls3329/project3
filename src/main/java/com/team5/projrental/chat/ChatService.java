package com.team5.projrental.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.chat.model.*;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.user.UserMapper;
import com.team5.projrental.user.model.UserEntity;
import com.team5.projrental.user.model.UserSelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.team5.projrental.common.exception.ErrorMessage.BAD_SAME_USER_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMapper mapper;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;
    private final AuthenticationFacade authenticationFacade;


    public List<ChatSelVo> getChatAll(ChatSelDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);
        List<ChatSelVo> list = mapper.selChatAll(dto);
        return list;
    }


    public ResVo postChatMsg(ChatMsgInsDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);
        dto.setMsg(CommonUtils.ifContainsBadWordChangeThat(dto.getMsg()));
        int istatus = mapper.delBeforeChatIstatus(dto);

        CommonUtils.ifChatUserStatusThrowOrReturn(istatus);

        int affectedRows = mapper.insChatMsg(dto);
        if (affectedRows == 1) {
            mapper.updChatLastMsg(dto);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdAt = now.format(formatter);

        UserEntity otherPerson = mapper.selOtherPersonByLoginUser(dto);


        try {
            if (otherPerson.getFirebaseToken() != null) {
                ChatMsgPushVo pushVo = new ChatMsgPushVo();
                pushVo.setIchat(dto.getIchat());
                pushVo.setSeq(dto.getSeq());
                pushVo.setWriterIuser(dto.getLoginedIuser());
                pushVo.setMsg(dto.getMsg());
                pushVo.setCreatedAt(createdAt);

                String body = objectMapper.writeValueAsString(pushVo);

                Notification noti = Notification.builder()
                        .setTitle("chat")
                        .setBody(body)
                        .build();

                Message message = Message.builder()
                        .setToken(otherPerson.getFirebaseToken())
                        .setNotification(noti)
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);


                // Async 비동기 - 내가 움직이는것과 상관없이 상대방도 움직일 수 있음
                // 동기 - 턴게임(장기, 체스) 내가 움직이면 상대방이 못움직임
                // 스레드 - 동작단위 (게임 예 : 총게임에 캐릭터 한명한명)
                // 메인스레드는 통신하지 말것

                // FirebaseMessaging fm = FirebaseMessaging.getInstance(); // 싱글톤
                // fm.sendAsync(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(dto.getSeq());
    }

    //채팅방 입장시 메세지 내용 불러오기
    public List<ChatMsgSelVo> getMsgAll(ChatMsgSelDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);

        List<ChatMsgSelVo> list = mapper.selChatMsgAll(dto);
        return list;
    }

    // 메세지 삭제(실제로 숨김처리)
    public ResVo chatDelMsg(ChatMsgDelDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        int updAffectedRows = mapper.updChatLastMsgAfterDelByLastMsg(dto);
        return new ResVo(updAffectedRows);
    }

    // 채팅 입력
    public ChatSelVo postChat(ChatInsDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);

        /*Integer isExixtChat = mapper.selChatUserCheck(dto);
            if (isExixtChat != null) {
            throw new BadInformationException(ErrorCode.ILLEGAL_EX_MESSAGE);
        }*/

        Integer existEnableRoom = mapper.selChatUserCheck2(dto);
        if (existEnableRoom == null || existEnableRoom == 0) {
            mapper.insChat(dto);


            mapper.insChatUser(ChatUserInsDto.builder()
                    .ichat(dto.getIchat())
                    .iuser(loginUserPk)
                    .build());

            mapper.insChatUser(ChatUserInsDto.builder()
                    .ichat(dto.getIchat())
                    .iuser(dto.getOtherPersonIuser())
                    .build());
        }

        /*UserSelDto usDto = new UserSelDto();
        usDto.setIuser(dto.getOtherPersonIuser());*/

        // 로그인 유저와 다른 유저정보 입력 오류메세지
        if (loginUserPk == dto.getOtherPersonIuser()) {
            throw new BadInformationException(BAD_SAME_USER_MESSAGE);
        }

        UserEntity entity = mapper.selChatUser(dto.getOtherPersonIuser());

        ChatSelVo vo = new ChatSelVo();
        vo.setIproduct(dto.getIproduct());
        vo.setOtherPersonIuser(entity.getIuser());
        vo.setOtherPersonNm(entity.getNick());
        vo.setOtherPersonPic(entity.getStoredPic());
        return vo;
    }
}
