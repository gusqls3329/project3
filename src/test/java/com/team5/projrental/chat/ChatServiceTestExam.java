package com.team5.projrental.chat;

import com.team5.projrental.chat.model.*;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.user.UserMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



/*@Slf4j
@ExtendWith(SpringExtension.class)
@Import({ChatService.class})
class ChatServiceTestExam {


    @MockBean
    private ChatMapper mapper;

    @MockBean
    private UserMapper userMapper;
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

    @Test
    void getChatAll() {
        ChatSelDto dto = new ChatSelDto();
        dto.setLoginedIuser(1);
        dto.setPage(1);
        dto.setRowCount(20);

        when(mapper.selChatAll(any())).thenReturn(null);

        ChatSelVo vo = new ChatSelVo();
        vo.setIproduct(25);
        vo.setIchat(2);
        vo.setIstatus(1);
        vo.setProdPic("prod\\25\\c5162906-2cb1-4a6c-a738-f675b20a67a4.jpg");
        vo.setOtherPersonPic("user\\7\\cfbf8730-7ce0-40bb-9a80-4e8987fe8866.jpg");
        vo.setOtherPersonNm("감자7");
        vo.setLastMsgAt("네 환영합니다. 내일 오세요");
        vo.setLastMsgAt("2024-01-19 13:02:03");

        ChatSelVo vo2 = new ChatSelVo();
        vo2.setIproduct(11);
        vo2.setIchat(5);
        vo2.setIstatus(-1);

        List<ChatSelVo> selVo = new ArrayList<>();
        selVo.add(vo);
        selVo.add(vo2);

        when(service.getChatAll(dto)).thenReturn(selVo);
        verify(mapper).selChatAll(any());
        assertEquals(25, selVo.get(0).getIproduct());
    }

    @Test
    void postChatMsg() {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setLoginedIuser(1);
        dto.setIchat(2);
        dto.setMsg("테스트메세지");

        int istatus = mapper.delBeforeChatIstatus(dto);

        CommonUtils.ifChatUserStatusThrowOrReturn(istatus);

        int affectedRows = mapper.insChatMsg(dto);
        if (affectedRows == 1) {
            mapper.updChatLastMsg(dto);
        }

        assertEquals(1, istatus);

    }

    @Test
    void getMsgAll() {
        ChatMsgSelDto dto = new ChatMsgSelDto();
        dto.setLoginedIuser(1);
        dto.setIchat(2);
        dto.setPage(1);

        ChatMsgSelVo vo = new ChatMsgPushVo();
        vo.setWriterIuser(7);
        vo.setProdPic("prod\\25\\c5162906-2cb1-4a6c-a738-f675b20a67a4.jpg");
        vo.setWriterPic("$2a$10$1EDxji4n1jJz7ycd0nHj/uwuXTO.VtZ020N5mNZ2hlKkW.f/P0Xku");
        vo.setCreatedAt("2024-01-19 13:01:29");
        vo.setMsg("안녕하세요 언제빌릴거에요?");
        vo.setSeq(5);

        List<ChatMsgSelVo> list = new ArrayList<>();
        list.add(vo);

        given(mapper.selChatMsgAll(dto)).willReturn(list);
        given(mapper.selChatMsgAll(any())).willReturn(list);

        assertEquals(7,list.get(0).getWriterIuser());
        assertEquals("prod\\25\\c5162906-2cb1-4a6c-a738-f675b20a67a4.jpg",list.get(0).getProdPic());
    }

    @Test
    void chatDelMsg() {
    }

    @Test
    void postChat() {
    }
}*/
