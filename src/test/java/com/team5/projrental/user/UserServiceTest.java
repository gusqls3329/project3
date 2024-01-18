package com.team5.projrental.user;

import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.security.JwtTokenProvider;
import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({UserService.class})
public class UserServiceTest {
    @MockBean
    private UserMapper mapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private SecurityProperties securityProperties;
    @MockBean
    private CookieUtils cookieUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;
    @MockBean
    private AxisGenerator axisGenerator;
    @MockBean
    private MyFileUtils myFileUtils;
    @Autowired
    private UserService service;


    @Test
    void postSignup() {
        when(mapper.insUser(any())).thenReturn(1);
        verify(mapper).insUser(any());
        int result = service.postSignup(any());
        assertEquals(result, 1);
    }

    @Test
    void postSignin() {

    }

    @Test
    void getSignOut() {
    }

    @Test
    void getRefrechToken() {
    }

    @Test
    void patchUserFirebaseToken() {
    }

    @Test
    void getFindUid() {
    }

    @Test
    void getFindUpw() {
    }

    @Test
    void putUser() {
    }

    @Test
    void patchUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void checkUserInfo() {
    }
}
