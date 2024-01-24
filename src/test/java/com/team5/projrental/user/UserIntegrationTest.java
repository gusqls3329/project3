package com.team5.projrental.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.user.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(rollbackFor = Exception.class)
public class UserIntegrationTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired UserController controller;

    AuthenticationFacade authenticationFacade;
    MultipartFile multipartFile;

    @Test
    void postSignup() throws Exception {
        UserSignupDto dto = new UserSignupDto();
        dto.setIuser(17);
        dto.setAddr("대구 동구 방촌동");
        dto.setRestAddr("AA아파트");
        dto.setUid("testuserid");
        dto.setUpw("testuserpw");
        dto.setNick("testnick");
        dto.setPhone("010-4114-9922");
        dto.setEmail("test@naver.com");
        dto.setIsValid(2);
       // dto.setChPic("pic.jpg");
        dto.setY(148);
        dto.setX(149.1);
        /*
        String fileName = "pic.jpg";
        String filePath = "D:/ee/" + fileName;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        this.multipartFile = new MockMultipartFile("pic", fileName, "png", fileInputStream);

        dto.setPic(this.multipartFile);

         */
        String json = objectMapper.writeValueAsString(dto);
        System.out.println(json);

        MvcResult mr = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();

        ResVo vo = objectMapper.readValue(content, ResVo.class); // json을 자바객체로?
        assertEquals(true, vo.getResult() > 0);


    }

    @Test
    void postSignin() throws Exception {
    }

    void getSignOut() throws Exception {}

    void getRefrechToken() throws Exception {}

    void patchUserFirebaseToken() throws Exception {}

    @Test
    void getFindUid() throws Exception {
        FindUidDto dto = new FindUidDto();
        dto.setPhone("010-7777-6666");

        String json = objectMapper.writeValueAsString(dto);

        MvcResult mr = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //FindUidVo vo = objectMapper.readValue(mr, FindUidVo.class)

    }

    @Test
    void getFindUpw() throws Exception {

    }

    @Test
    void putUser () throws Exception {

    }

    @Test
    void patchUser () throws Exception {

    }

    @Test
    void getUSer () throws Exception {

    }

}
