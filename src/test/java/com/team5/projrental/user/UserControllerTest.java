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
        UserSignupDto dto = new UserSignupDto();
        dto.setAddr("대구 동구 방촌동");
        dto.setRestAddr("a아파트");
        dto.setUid("testuid");
        dto.setUpw("testupw");
        dto.setNick("testNickname");
        dto.setPhone("010-4114-9922");
        dto.setEmail("khi05040@naver.com");
        dto.setIsValid(2);
        ResVo reuslt = new ResVo(1);
        //given(service.postSignup(dto)).willReturn(Const.SUCCESS);
       mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(reuslt)))
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
        //FindUidVo vo = new FindUidVo();
        FindUidVo vo = service.getFindUid(dto);

       given(service.getFindUid(dto)).willReturn(vo);
       /* mvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(vo)));

        */
        verify(service).getFindUid(dto);

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
        dto.setNick("testnick");
        int result = service.putUser(dto);
        given(service.putUser(dto)).willReturn(result);
        /*
        mvc.perform(MockMvcRequestBuilders
                .put("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

         */

        verify(service).putUser(dto);
    }

    void patchUser () throws Exception {}

    @Test
    void getUSer () throws Exception {
        SelUserVo vo = new SelUserVo();
        MultiValueMap<String, String> params = new LinkedMultiValueMap();
        //params.add();

       // mvc.perform(MockMvcRequestBuilders
         //       .get("/api/user")
    }
}
