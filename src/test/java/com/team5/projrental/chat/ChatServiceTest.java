package com.team5.projrental.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.team5.projrental.chat.model.*;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.user.UserMapper;
import com.team5.projrental.user.model.UserEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;



@Slf4j
@ExtendWith(SpringExtension.class)
@Import(ChatService.class)
class ChatServiceTest {

    @MockBean
    private ChatMapper mapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private SecurityProperties securityProperties;
    @MockBean
    private CookieUtils cookieUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;
    @MockBean
    private KakaoAxisGenerator axisGenerator;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    private HttpServletResponse res;
    @Autowired
    private ChatService service;
    @MockBean
    private ObjectMapper objectMapper;
    @MockBean
    private UserMapper userMapper;

    @Test
    void getChatAll() {
        ChatSelDto dto = new ChatSelDto();
        dto.setLoginedIuser(1);
        dto.setPage(1);
        dto.setRowCount(10);
        dto.setStartIdx(1);

        ChatSelVo vo = new ChatSelVo();
        vo.setIchat(2);
        vo.setIproduct(25);

        List<ChatSelVo> list2 = new ArrayList<>();
        list2.add(vo);


        when(mapper.selChatAll(dto)).thenReturn(list2);


        List<ChatSelVo> list = service.getChatAll(dto);
        verify(mapper).selChatAll(any());
        assertEquals(list.get(0).getIchat(),2);
        assertEquals(list.get(0).getIproduct(), 25);
    }

    /*@Test
    void postChatMsg() {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setLoginedIuser(1);
        dto.setMsg("하이 테스트");
        dto.setIchat(2);
        dto.setSeq(7);

        int istatus = mapper.delBeforeChatIstatus(dto);

        assertEquals(0,istatus);

        int affectedRows = mapper.insChatMsg(dto);

        assertEquals(1, affectedRows);

        if (affectedRows == 1) {
            int updRows = mapper.updChatLastMsg(dto);

            assertEquals(1,updRows);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포멧 정의
        String createdAt = now.format(formatter);

        UserEntity otherPerson = mapper.selOtherPersonByLoginUser(dto);

        assertEquals(7,otherPerson.getIuser());

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
                        .putData("type", "chat")
                        .putData("json", body)
                        .setToken(otherPerson.getFirebaseToken())
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(dto.getSeq(),7);
    }*/

    @Test
    void getMsgAll() {
        ChatMsgSelDto dto = new ChatMsgSelDto();
        dto.setIchat(11);
        dto.setPage(1);
        dto.setLoginedIuser(1);

        ChatMsgSelVo vo = new ChatMsgPushVo();
        vo.setMsg("테스트메세지1");
        vo.setSeq(1);
        vo.setCreatedAt("2024-01-29 13:07:47");
        vo.setWriterIuser(7);
        vo.setProdPic("prod\\25\\c5162906-2cb1-4a6c-a738-f675b20a67a4.jpg");
        vo.setWriterPic("user\\7\\cfbf8730-7ce0-40bb-9a80-4e8987fe8866.jpg");

        List<ChatMsgSelVo> list = new ArrayList<>();
        list.add(vo);

        given(mapper.selChatMsgAll(dto)).willReturn(list);
        given(mapper.selChatMsgAll(any())).willReturn(list);

        list = service.getMsgAll(dto);
        verify(mapper).selChatMsgAll(any());
        assertEquals(list.get(0).getMsg(), "테스트메세지1");
        assertEquals(list.get(0).getSeq(),1);
    }

    @Test
    void chatDelMsg() {
        ChatMsgDelDto dto = new ChatMsgDelDto();
        dto.setIuser(1);
        dto.setIchat(7);

        when(mapper.updChatLastMsgAfterDelByLastMsg(dto)).thenReturn(1);

        ResVo vo = service.chatDelMsg(dto);
        verify(mapper).updChatLastMsgAfterDelByLastMsg(dto);
        assertEquals(vo, new ResVo(1));
    }

    @Test
    void postChat() {
        ChatInsDto dto = new ChatInsDto();
        dto.setLoginedIuser(1);
            dto.setIchat(11);
        dto.setIproduct(25);
        dto.setOtherPersonIuser(7);

        when(mapper.selChatUserCheck2(dto)).thenReturn(1);
        Integer existEnableRoom = mapper.selChatUserCheck2(dto);
        assertEquals(1,existEnableRoom);

        when(mapper.insChat(dto)).thenReturn(1);
        int affectedinsChat = mapper.insChat(dto);
        assertEquals(1, affectedinsChat);


    }
}
