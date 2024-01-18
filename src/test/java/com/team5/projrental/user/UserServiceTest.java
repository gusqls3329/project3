package com.team5.projrental.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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
