package com.team5.projrental.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.ErrorCode;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.chat.model.*;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.user.UserMapper;
import com.team5.projrental.user.model.UserEntity;
import com.team5.projrental.user.model.UserSelDto;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMapper mapper;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;
    private final AuthenticationFacade authenticationFacade;


    //채팅 리스트
    public List<ChatSelVo> getChatAll(ChatSelDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);
        List<ChatSelVo> list = mapper.selChatAll(dto);
        return list;
    }

    //
    public ResVo postChatMsg(ChatMsgInsDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);

        int istatus = mapper.delBeforeChatIstatus(dto);



        //상대유저가 나갔을 경우
        CommonUtils.ifChatUserStatusThrowOrReturn(istatus);

        int affectedRows = mapper.insChatMsg(dto);
        if (affectedRows == 1) {
            int updAffectedRows = mapper.updChatLastMsg(dto);
        }

        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포멧 정의
        String createdAt = now.format(formatter); // 포멧 적용

        //상대방의 firebaseToken값 필요. 나의 pic, iuser값 필요
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
                log.info("body: {}", body);

                Notification noti = Notification.builder()
                        .setTitle("dm") // 제모각성, 프론트에서 쓰는 분기용
                        .setBody(body) // 내용
                        .build();

                Message message = Message.builder()
                        .putData("type", "dm")
                        .putData("json", body)
                        .setToken(otherPerson.getFirebaseToken())
                        //.setNotification(noti) 이거 넣으면 채팅 입력시 메세지가 중복해서 2개씩 표현됨
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);
                // Async 비동기 - 내가 움직이는것과 상관없이 상대방도 움직일 수 있음
                // 동기 - 턴게임(장기, 체스) 내가 움직이면 상대방이 못움직임
                // 스레드 - 동작단위 (게임 예 : 총게임에 캐릭터 한명한명)
                // 메인스레드는 통신하지 말것

                FirebaseMessaging fm = FirebaseMessaging.getInstance(); // 싱글톤
                fm.sendAsync(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(dto.getSeq());
    }

    //채팅방 입장시 메세지 내용 불러오기
    public List<ChatMsgSelVo> getMsgAll(ChatMsgSelDto dto) {
        List<ChatMsgSelVo> list = mapper.selChatMsgAll(dto);
        return list;
    }

    public ResVo chatDelMsg(ChatMsgDelDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        int updAffectedRows = mapper.updChatLastMsgAfterDelByLastMsg(dto);
        return new ResVo(updAffectedRows);
    }

    // 채팅 입력
    public ChatSelVo postChat(ChatInsDto dto) {
        Integer isExixtChat = mapper.selChatUserCheck(dto);
        log.info("dto: {}", dto);
        if (isExixtChat != null) {
            return null;
        }
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setLoginedIuser(loginUserPk);
        mapper.insChat(dto);

        mapper.insChatUser(ChatUserInsDto.builder()
                .ichat(dto.getIchat())
                .iuser(loginUserPk)
                .build());

        mapper.insChatUser(ChatUserInsDto.builder()
                .ichat(dto.getIchat())
                .iuser(dto.getOtherPersonIuser())
                .build());


        UserSelDto usDto = new UserSelDto();
        usDto.setIuser(dto.getOtherPersonIuser());

        UserEntity entity = mapper.selChatUser(usDto.getIuser());

        ChatSelVo vo = new ChatSelVo();
        vo.setIproduct(dto.getIproduct());
        vo.setOtherPersonIuser(entity.getIuser());
        vo.setOtherPersonNm(entity.getNick());
        vo.setOtherPersonPic(entity.getStoredPic());
        return vo;
    }

}
