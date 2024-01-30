package com.team5.projrental.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.MockMvcConfig;
import com.team5.projrental.chat.model.ChatInsDto;
import com.team5.projrental.chat.model.ChatMsgDelDto;
import com.team5.projrental.chat.model.ChatMsgInsDto;
import com.team5.projrental.chat.model.ChatSelVo;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.ProductController;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@MockMvcConfig
@Import(ChatController.class)
@Transactional
class ChatControllerTest {

    String token;

    @MockBean
    private ChatService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ChatController controller;


    @BeforeEach
    public void before() throws Exception {
        SigninDto dto = new SigninDto();
        dto.setUid("qwqwqw11");
        dto.setUpw("12121212");

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = objectMapper.readValue(contentAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();
    }

    @Test
    void getChatAll() {

    }

    @Test
    void postChat() throws Exception{
        ChatInsDto dto = new ChatInsDto();
        dto.setIchat(6);
        dto.setLoginedIuser(1);
        dto.setOtherPersonIuser(7);
        dto.setIproduct(25);

        ChatSelVo vo = new ChatSelVo();
        vo.setIstatus(0);
        vo.setIchat(2);
        vo.setIproduct(25);
        vo.setProdPic("prod\\25\\c5162906-2cb1-4a6c-a738-f675b20a67a4.jpg");
        vo.setLastMsg("네 환영합니다. 내일 오세요");
        vo.setLastMsgAt("2024-01-19 13:02:03");
        vo.setOtherPersonIuser(7);
        vo.setOtherPersonNm("감자7");
        vo.setOtherPersonPic("user\\7\\cfbf8730-7ce0-40bb-9a80-4e8987fe8866.jpg");

        given(service.postChat(any())).willReturn(vo);


        String response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/chat")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vo)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();



        ChatSelVo result = objectMapper.readValue(response, ChatSelVo.class);
        System.out.println("result = " + result);
        Assertions.assertEquals(vo.getIstatus(), result.getIstatus());
        Assertions.assertEquals(vo.getIchat(), result.getIchat());
        Assertions.assertEquals(vo.getIproduct(), result.getIproduct());
        Assertions.assertEquals(vo.getLastMsgAt(), result.getLastMsgAt());
        Assertions.assertEquals(vo.getOtherPersonIuser(), result.getOtherPersonIuser());

        verify(service).postChat(any());
    }

    @Test
    void postChatControllerTest() throws Exception{
        ChatInsDto dto = new ChatInsDto();
        dto.setIchat(6);
        dto.setLoginedIuser(1);
        dto.setOtherPersonIuser(100);
        dto.setIproduct(25);

        ChatSelVo vo = new ChatSelVo();
        vo.setIstatus(0);
        vo.setIchat(2);
        vo.setIproduct(25);
        vo.setProdPic("prod\\25\\c5162906-2cb1-4a6c-a738-f675b20a67a4.jpg");
        vo.setLastMsg("네 환영합니다. 내일 오세요");
        vo.setLastMsgAt("2024-01-19 13:02:03");
        vo.setOtherPersonIuser(7);
        vo.setOtherPersonNm("감자7");
        vo.setOtherPersonPic("user\\7\\cfbf8730-7ce0-40bb-9a80-4e8987fe8866.jpg");

        given(service.postChat(any())).willReturn(vo);

        ChatSelVo result = controller.PostChat(dto);

        Assertions.assertEquals(vo, result);

        Assertions.assertEquals(vo.getIstatus(), result.getIstatus());
        Assertions.assertEquals(vo.getIchat(), result.getIchat());
        Assertions.assertEquals(vo.getIproduct(), result.getIproduct());
        Assertions.assertEquals(vo.getLastMsgAt(), result.getLastMsgAt());
        Assertions.assertEquals(vo.getOtherPersonIuser(), result.getOtherPersonIuser());
        verify(service).postChat(dto);

    }

    @Test
    void postChatMsg() throws Exception {
        ChatMsgInsDto dto = new ChatMsgInsDto();
        dto.setIchat(2);
        dto.setMsg("안녕하세요 테스트중입니다.2");
        given(service.postChatMsg(any())).willReturn(new ResVo(Const.SUCCESS));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/chat/msg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":1}"));
        verify(service).postChatMsg(dto);
    }

    @Test
    void getChatMsgAll() {

    }

    @SneakyThrows
    @Test
    void delChatMsg() {
        ResVo vo = new ResVo(Const.SUCCESS);
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("iuser", "1");
        requestParams.add("ichat", "2");

        given(service.chatDelMsg(any(ChatMsgDelDto.class))).willReturn(new ResVo(Const.SUCCESS));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/chat/msg")
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":1}"))
                .andDo(print());
        verify(service).chatDelMsg(any());
    }
}