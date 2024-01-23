package com.team5.projrental.user;

import com.team5.projrental.common.Const;
import com.team5.projrental.user.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(rollbackFor = Exception.class)
public class UserIntegrationTest {
    @Test
    void postSignup() throws Exception {
    }

    @Test
    void postSignin() throws Exception {
    }

    void getSignOut() throws Exception {}

    void getRefrechToken() throws Exception {}

    void patchUserFirebaseToken() throws Exception {}

    @Test
    void getFindUid() throws Exception {

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
