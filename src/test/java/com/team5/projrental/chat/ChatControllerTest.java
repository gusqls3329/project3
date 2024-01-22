package com.team5.projrental.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.chat.model.ChatInsDto;
import com.team5.projrental.chat.model.ChatMsgDelDto;
import com.team5.projrental.chat.model.ChatSelVo;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.ProductController;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
class ChatControllerTest {

    String token;

    @MockBean
    private ChatService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    ProductController controller;


    /*@BeforeEach
    public void before() throws Exception {
        SigninDto dto = new SigninDto();
        dto.setUid("qwqwqw11");
        dto.setUpw("12121212");

        String contestAsString = mockMvc.perform(MockMvcRequestBuilders.post("/api/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = mapper.readValue(contestAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();
    }*/

    @Test
    void getChatAll() {

    }

    /*@Test
    void postChat() {
        ChatInsDto dto = new ChatInsDto();
        dto.setIchat(6);
        dto.setLoginedIuser(1);
        dto.setOtherPersonIuser(2);
        dto.setIproduct(12);

        ChatSelVo vo = new ChatSelVo();
        vo.getIchat();
        vo.getIproduct();
        vo.getProdPic();
        vo.getLastMsg();
        vo.getLastMsgAt();
        vo.getOtherPersonIuser();
        vo.getOtherPersonNm();
        vo.getOtherPersonPic();

        given(service.postChat(any())).willReturn(vo);

        mockMvc.perform(MockMvcRequestBuilders
                .post("api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json());
    }*/

    @Test
    void postChatMsg() {

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
                .andExpect(content().string(mapper.writeValueAsString(vo)))
                .andDo(print());

    }
}