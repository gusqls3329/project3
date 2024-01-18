package com.team5.projrental.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.filter.JwtAuthenticationFilter;
import com.team5.projrental.user.model.FindUidDto;
import com.team5.projrental.user.model.UserSignupDto;
import org.apache.commons.codec.CharEncoding;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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


    @Test
    @DisplayName("회원가입 테스트")
    void postSignup() throws Exception {
        ResVo result = new ResVo(2);
        UserSignupDto dto = new UserSignupDto();
        given(service.postSignup(any())).willReturn(result.getResult());

        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writer().writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).postSignup(any());
    }

    @Test
    void postSignin() throws Exception {
    }

    void getSignOut() throws Exception {}

    void getRefrechToken() throws Exception {}

    void patchUserFirebaseToken() throws Exception {}

    @Test
    void getFindUid() throws Exception {
        ResVo result = new ResVo(2);
        FindUidDto dto = new FindUidDto();
        given(service.getFindUid(any())).willReturn();
    }

    void getFindUpw() throws Exception {}

    void putUser () throws Exception {}

    void patchUser () throws Exception {}

    void getUSer () throws Exception {}
}
