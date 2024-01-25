package com.team5.projrental.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.user.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.*;
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
//@Rollback(value = false)
public class UserIntegrationTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    String token;

    @Autowired UserController controller;

    @Autowired UserService service;
    AuthenticationFacade authenticationFacade;
    MultipartFile multipartFile;

    @Test
    void postSignup() throws Exception {
        UserSignupDto dto = new UserSignupDto();
        //dto.setIuser(12);
        dto.setAddr("대구 동구 방촌동");
        dto.setRestAddr("AA아파트");
        dto.setUid("testuserid");
        dto.setUpw("testuserpw");
        dto.setNick("testnick");
        dto.setPhone("010-4114-9922");
        dto.setEmail("test@naver.com");
        dto.setIsValid(2);
        //int userTest = service.postSignup(dto);
        //dto.setX(123);
        //dto.setY(124);
        //dto.setChPic("pic.jpg");

        String fileName = "pic.jpg";
        String filePath = "D:/ee/" + fileName;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        this.multipartFile = new MockMultipartFile("pic", fileName, "png", fileInputStream);

        //dto.setPic(this.multipartFile);

        String response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ResVo result = objectMapper.readValue(response, ResVo.class);
        Assertions.assertEquals(Const.SUCCESS, result.getResult());



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
        dto.setPhone("010-1111-1111");

        String json = objectMapper.writeValueAsString(dto);
//        FindUidVo result = controller.getFindUid(dto);
//        FindUidVo result2 = service.getFindUid(dto);
//        assertEquals(result.getUid(), result2.getUid());
        String response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        FindUidVo result = objectMapper.readValue(response, FindUidVo.class);
        FindUidVo vo = new FindUidVo();
        vo.setUid("qwqwqw11");
        Assertions.assertEquals(vo.getUid(), result.getUid());

        /*MvcResult mr = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //FindUidVo vo = objectMapper.readValue(mr, FindUidVo.class)*/

    }

    @Test
    void getFindUpw() throws Exception {

        // ---
        FindUpwDto dto = new FindUpwDto();
        dto.setUid("qwqwqw11");
        dto.setPhone("010-1111-1111");
        dto.setUpw("12121212");

        String response = mvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/user/pw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ResVo result = objectMapper.readValue(response, ResVo.class);
        Assertions.assertEquals(Const.SUCCESS, result.getResult());
    }

    @Test
    void putUser () throws Exception {
        SigninDto sDto = new SigninDto();
        sDto.setUid("qwqwqw11");
        sDto.setUpw("12121212");

        String contentAsString = mvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sDto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = objectMapper.readValue(contentAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();

        // -----------
        ChangeUserDto dto = new ChangeUserDto();

        dto.setIuser(signinVo.getIuser());
        dto.setUpw("testuserpw12");
        dto.setNick("dongdongtest");

        String response = mvc.perform(
                MockMvcRequestBuilders
                        .put("/api/user")
                        //.header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ResVo result = objectMapper.readValue(response, ResVo.class);
        Assertions.assertEquals(Const.SUCCESS, result.getResult());

    }

    @Test
    void patchUser () throws Exception {
        SigninDto sDto = new SigninDto();
        sDto.setUid("qwqwqw123");
        sDto.setUpw("qwqwqw123123");

        String contentAsString = mvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sDto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = objectMapper.readValue(contentAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();
        // -------

        DelUserDto dto = new DelUserDto();
        dto.setIuser(signinVo.getIuser());
        dto.setUid("qwqwqw11");
        dto.setUpw("12121212");
        dto.setPhone("010-1111-1111");

        String response = mvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/user")
                        //.header("Authorization", this.token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ResVo result = objectMapper.readValue(response, ResVo.class);
        Assertions.assertEquals(Const.SUCCESS, result.getResult());

    }

    @Test
    void getUSer () throws Exception {

    }

}
