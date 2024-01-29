package com.team5.projrental.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.MockMvcConfig;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.security.filter.JwtAuthenticationFilter;
import com.team5.projrental.user.model.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.CharEncoding;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockMvcConfig
@Import(UserController.class)
public class UserControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private UserService service;
    @MockBean private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockBean private AuthenticationFacade authenticationFacade;
    @Autowired private UserController controller;


    @Test
    @DisplayName("회원가입 테스트")
    void postSignup() throws Exception {
        UserSignupDto dto = new UserSignupDto();
        dto.setUid("testuserid");
        dto.setUpw("testuserpw");
        dto.setPhone("010-4114-9922");
        dto.setEmail("khi05040@naver.com");

        //ResVo result = new ResVo(1);
        //int reuslt = service.postSignup(dto);
        given(service.postSignup(any())).willReturn(Const.SUCCESS);
        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                //.andExpect(content().string(mapper.writeValueAsString(Const.SUCCESS)))
                .andDo(print());
        //verify(service).postSignup(dto);
    }

    @Test
    @DisplayName("로그인 테스트")
    void postSignin() throws Exception {
        SigninDto dto = new SigninDto();
        dto.setUid("dongdong12");
        dto.setUpw("dongdong12");
        SigninVo vo = SigninVo.builder()
                .uid("dongdong12")
                .upw("testuserpw")
                .build();
        given(service.postSignin(any(), any())).willReturn(vo);
        mvc.perform(MockMvcRequestBuilders
                .post("/api/user/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());


    }

    void getSignOut() throws Exception {}

    void getRefrechToken() throws Exception {}

    void patchUserFirebaseToken() throws Exception {}

    @Test
    void getFindUid() throws Exception {
        FindUidDto dto = new FindUidDto();
        dto.setPhone("010-7777-6666");

        FindUidVo vo = new FindUidVo();
        vo.setUid("dongdong12");
        vo.setIuser(14);

        //String json = objectMapper.writeValueAsString(dto);
        //System.out.println(json);
       given(service.getFindUid(any())).willReturn(vo);
       mvc.perform(
                    MockMvcRequestBuilders
                            .patch("/api/user/id")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
               )
                .andExpect(status().isOk())
                .andDo(print());
                //.andExpect(content().string(objectMapper.writeValueAsString(vo)));
        //verify(service).getFindUid(dto);

    }

    @Test
    void getFindUpw() throws Exception {
        FindUpwDto dto = new FindUpwDto();
        //ResVo result = new ResVo(1);
        dto.setPhone("010-7777-6666");
        dto.setUid("dongdong12");
        dto.setUpw("testpassword");

        given(service.getFindUpw(dto)).willReturn(Const.SUCCESS);
        mvc.perform(MockMvcRequestBuilders
                    .post("/api/user/pw")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());

        //verify(service).getFindUpw(dto);
    }

    @Test
    void putUser () throws Exception {
        ChangeUserDto dto = new ChangeUserDto();
        int loginedIuser = authenticationFacade.getLoginUserPk();
        dto.setNick("testnick");
        dto.setIuser(loginedIuser);

        //given(service.putUser(any())).willReturn(Const.SUCCESS);

        mvc.perform(MockMvcRequestBuilders
                    .put("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());
                //.andExpect(content().string(objectMapper.writeValueAsString(result)))

       // verify(service).putUser(dto);
    }

    @Test
    void patchUser () throws Exception {
        DelUserDto dto = new DelUserDto();
        int logindIuser =authenticationFacade.getLoginUserPk();
        dto.setIuser(logindIuser);
        dto.setUid("dongdong12");
        dto.setUpw("dongdong12");
        dto.setPhone("010-7777-6666");

        given(service.patchUser(any())).willReturn(Const.SUCCESS);

        mvc.perform(MockMvcRequestBuilders
                .patch("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void getUSer () throws Exception {
        int iuser = 14;
        SelUserVo vo = new SelUserVo();
        given(service.getUser(any())).willReturn(vo);
        mvc.perform(MockMvcRequestBuilders
                .get("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(iuser)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
