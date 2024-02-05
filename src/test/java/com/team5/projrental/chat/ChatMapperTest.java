package com.team5.projrental.chat;

import com.team5.projrental.chat.model.*;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import com.team5.projrental.user.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@MybatisTest // xml파일(Dao)만 전부다 빈등록 됨.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatMapperTest {

    @Autowired
    private ChatMapper mapper;

        /*selVo.setIchat(1);
        selVo.setIproduct(1);
        selVo.setIstatus(1);
        selVo.setLastMsg("하이용");
        selVo.setOtherPersonIuser(2);
        selVo.setOtherPersonNm("바보현빈");
        selVo.setOtherPersonPic("aaa.jpg");
        selVo.setLastMsgAt("2024-01-25");
        selVo.setProdPic("ggg.jpg");*/


    @Test
    void insChat() { // Iproduct가 있는 pk번호로 만들어야 함
        ChatInsDto dto = new ChatInsDto();
        dto.setIproduct(25);
        int iChat = mapper.insChat(dto);
        log.info("result : {}", iChat);
        assertEquals(1, iChat);

    }

    @Test
    void selChatAll() {
        ChatSelDto selDto = new ChatSelDto();
        selDto.setLoginedIuser(1);
        selDto.setPage(1);
        selDto.setRowCount(10);

        List<ChatSelVo> result = mapper.selChatAll(selDto);
        log.info("resultsize : {}", result.size());
        assertEquals(2, result.size());
    }

    //ichat 2번방에 iuser값이 이미 있어서 DB에 지우고하면 됨
    // 아니면 새로운 상품에 채팅방만들어서 test하기
    @Test
    void insChatUser() { // 채팅방이 미리 생성되어있는곳에 없는 유저를 넣어야 성공함
        ChatUserInsDto dto = ChatUserInsDto.builder()
                .ichat(2)
                .iuser(1)
                .build();


        int insChatUserSize = mapper.insChatUser(dto);

        log.info("resultsize : {}", insChatUserSize);
        assertEquals(1, insChatUserSize);
    }

    @Test
    void selChatUserCheck() {
        ChatInsDto dto = new ChatInsDto();
        dto.setLoginedIuser(1);
        dto.setOtherPersonIuser(7);
        dto.setIchat(2);
        dto.setIproduct(25);

        Integer ichat = mapper.selChatUserCheck(dto);

        assertEquals(2,ichat);

    }

    @Test
    void insChatMsg() {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setMsg("하이");
        dto.setIchat(2);
        dto.setLoginedIuser(1);

        int chatMsg = mapper.insChatMsg(dto);

        assertEquals(1, chatMsg);

    }

    //쿼리문에 order by 및 group by MySQL에는 사용불가능한듯 윈도우로 확인해보기
@Test
    void selChatMsgAll() {
        ChatMsgSelDto dto = new ChatMsgSelDto();
        dto.setLoginedIuser(1);
        dto.setIchat(5);
        dto.setPage(1);
        dto.setStartIdx(1);
        dto.setRowCount(20);

        List<ChatMsgSelVo> list = mapper.selChatMsgAll(dto);
        assertEquals(7, list.size());

    }

    @Test
    void updChatLastMsgAfterDelByLastMsg() {
        ChatMsgDelDto dto = new ChatMsgDelDto();
        dto.setIuser(1);
        dto.setIchat(2);
        int affectedDelChat = mapper.updChatLastMsgAfterDelByLastMsg(dto);
        assertEquals(1, affectedDelChat);
    }

    @Test
    void updChatLastMsg() {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setIchat(2);
        dto.setMsg("테스트");

        int affectedLastChat = mapper.updChatLastMsg(dto);
        assertEquals(1, affectedLastChat);
    }

    @Test
    void selOtherPersonByLoginUser() {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setIchat(5);
        dto.setMsg("하이테스트");
        dto.setSeq(7);
        dto.setLoginedIuser(1);
        List<UserEntity> userEntityList = new ArrayList<>();

        UserEntity entity = mapper.selOtherPersonByLoginUser(dto);
        log.info("entity : {}", entity);

        userEntityList.add(entity);

        assertEquals(1, userEntityList.size());

    }

    @Test
    void selChatUser() {
        int iuser = 1;

        UserEntity entity = mapper.selChatUser(iuser);
        assertEquals(1, entity.getIuser());
        assertEquals("바보현빈", entity.getNick());
        assertEquals("user\\1\\5dd05f9e-12f3-42ae-a53f-727f9c76d40f.jpg", entity.getStoredPic());
    }

    @Test
    void delBeforeChatIstatus() {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setLoginedIuser(1);
        dto.setIchat(5);


        int istatus = mapper.delBeforeChatIstatus(dto);

        assertEquals(0, istatus);
    }
}