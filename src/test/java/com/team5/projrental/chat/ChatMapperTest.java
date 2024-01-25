package com.team5.projrental.chat;

import com.team5.projrental.chat.model.ChatInsDto;
import com.team5.projrental.chat.model.ChatSelDto;
import com.team5.projrental.chat.model.ChatSelVo;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@MybatisTest // xml파일(Dao)만 전부다 빈등록 됨.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatMapperTest {

    @Autowired
    private ChatMapper mapper;



    @Test
    void insChat() {
        ChatInsDto dto = new ChatInsDto();
        dto.setLoginedIuser(1);
        dto.setIproduct(1);
        dto.setIchat(1);
        dto.setOtherPersonIuser(2);




    }

    @Test
    void selChatAll() {
        ChatSelDto selDto = new ChatSelDto();
        selDto.setLoginedIuser(1);
        selDto.setPage(1);
        selDto.setRowCount(10);
        selDto.setStartIdx(1);

        List<ChatSelVo> result = mapper.selChatAll(selDto);
        log.info("resultsize : {}", result.size());
        assertEquals(1, result.size());
    }

    @Test
    void insChatUser() {

    }

    @Test
    void selChatUserCheck() {
    }

    @Test
    void insChatMsg() {
    }

    @Test
    void selChatMsgAll() {
    }

    @Test
    void updChatLastMsgAfterDelByLastMsg() {
    }

    @Test
    void updChatLastMsg() {
    }

    @Test
    void selOtherPersonByLoginUser() {
    }

    @Test
    void selChatUser() {
    }

    @Test
    void delBeforeChatIstatus() {
    }
}