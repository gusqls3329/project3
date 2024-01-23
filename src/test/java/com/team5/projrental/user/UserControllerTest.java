package com.team5.projrental.user;


import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper mapper;
    @MockBean private UserService service;
    @MockBean private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockBean private AuthenticationFacade authenticationFacade;
    @Autowired private UserController controller;



    @Test
    @DisplayName("회원가입 테스트")
    void postSignup() throws Exception {
        //UserSignupDto dto = new UserSignupDto();
        Map<String, String> map = new HashMap<>();
        map.put("addr", "대구 동구 방촌동");
        map.put("restAddr", "a아파트");
        map.put("uid", "testuserid");
        map.put("upw", "testuserpw");
        map.put("nick", "testNickname");
        map.put("phone", "010-4114-9922");
        map.put("email", "khi05040@naver.com");
        map.put("IsValid", "2");
        //ResVo result = new ResVo(1);
        //int reuslt = service.postSignup(dto);
        //given(service.postSignup(dto)).willReturn(reuslt);
       mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(map))
                )
                .andExpect(status().isOk())
                //.andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());
        //verify(service).postSignup(dto);
    }

    @Test
    void postSignin() throws Exception {
        SigninDto dto = new SigninDto();
        dto.setUid("dongdong12");
        dto.setUpw("dongdong12");
        // given(service.postSignin(any(), any())).willReturn();
    }

    void getSignOut() throws Exception {}

    void getRefrechToken() throws Exception {}

    void patchUserFirebaseToken() throws Exception {}

    @Test
    void getFindUid() throws Exception {
        FindUidDto dto = new FindUidDto();
        dto.setPhone("010-7777-6666");
        FindUidVo vo = service.getFindUid(dto);

        String json = mapper.writeValueAsString(dto);
        System.out.println(json);

       //given(service.getFindUid(dto)).willReturn(vo);
       mvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());
                //.andExpect(status().isOk())
                //.andExpect(content().json("{\"iuser\":\"14\",\"uid\":\"dongdong12\"}"));

        //verify(service).getFindUid(dto);

    }

    @Test
    void getFindUpw() throws Exception {
        FindUpwDto dto = new FindUpwDto();
        dto.setPhone("010-7777-6666");
        dto.setUid("dongdong12");
        dto.setUpw("testpassword");

        given(service.getFindUpw(dto)).willReturn(Const.SUCCESS);
    /*
        mvc.perform(MockMvcRequestBuilders
                    .post("/api/user/pw")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(vo)));
     */
        verify(service).getFindUpw(dto);
    }

    @Test
    void putUser () throws Exception {
        ChangeUserDto dto = new ChangeUserDto();
        int loginedIuser = authenticationFacade.getLoginUserPk();
        dto.setNick("testnick");
        dto.setIuser(loginedIuser);
        int result = service.putUser(dto);
        //given(service.putUser(dto)).willReturn(result);

        mvc.perform(MockMvcRequestBuilders
                .put("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

       // verify(service).putUser(dto);
    }

    void patchUser () throws Exception {}

    @Test
    void getUSer () throws Exception {
        SelUserVo vo = new SelUserVo();
        //MultiValueMap<String, String> params = new LinkedMultiValueMap();
    }
}
