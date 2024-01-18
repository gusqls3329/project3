package com.team5.projrental.user;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.security.JwtTokenProvider;
import com.team5.projrental.common.security.model.SecurityPrincipal;
import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.user.model.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Slf4j
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
    @MockBean
    private HttpServletResponse res;
    @Autowired
    private UserService service;


    @Test
    void postSignup() {
        when(mapper.insUser(any())).thenReturn(1);

        UserSignupDto dto = new UserSignupDto();
        dto.setUpw("12121212");
        dto.setUid("serTest");
        dto.setEmail("test@nacer.com");
        dto.setNick("서비스테스트");
        dto.setPhone("010-1111-1112");
        dto.setAddr("경기도 군포시 산본동");
        dto.setRestAddr("11 sdfs");
        Addrs addrs = new Addrs();
        addrs.setAddress_name("대구 달서구 용산동");
        addrs.setX("1.1");
        addrs.setY("1.2");
        when(axisGenerator.getAxis(any())).thenReturn(addrs);

        dto.setAddr(addrs.getAddress_name());


        int result = service.postSignup(dto);
        assertEquals(result, 1);

    }

    @Test
    void postSignin() {
        SigninDto dto = new SigninDto();
 // Assuming this is the plain password

        UserEntity entity = new UserEntity();
        dto.setUid("12341234");
        dto.setUpw("12341234");

        entity.setUid(dto.getUid());
        entity.setUpw(dto.getUpw());
        entity.setIuser(1);
        entity.setFirebaseToken("123");
        when(passwordEncoder.encode(dto.getUpw())).thenReturn("hashedPassword");

        when(mapper.selSignin(any())).thenReturn(entity);
        SecurityPrincipal principal = SecurityPrincipal.builder().iuser(entity.getIuser()).build();
        when(jwtTokenProvider.generateAccessToken(principal)).thenReturn("mockedAccessToken");
        when(jwtTokenProvider.generateRefreshToken(principal)).thenReturn("mockedRefreshToken");

        log.info("entity:{}",entity);
        log.info("dto:{}",dto.getUpw());

        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        SigninVo vo = service.postSignin(null, dto);


        verify(mapper).selSignin(any());
//        verify(passwordEncoder).encode(dto.getUpw()); // Verify password encoding was called
//        verify(cookieUtils).deleteCookie(res, "rt");
//        verify(cookieUtils).setCookie(eq(res), eq("rt"), eq("mockedRefreshToken"), anyInt());

//        assertEquals(vo.getUid(), entity.getUid());
        assertEquals(vo.getIuser(), entity.getIuser());
        assertEquals(vo.getFirebaseToken(), entity.getFirebaseToken());
        assertEquals(vo.getResult(), String.valueOf(Const.SUCCESS));

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
        FindUidDto dto = new FindUidDto();
        dto.setPhone("010-1234-5678");

        when(mapper.selFindUid(dto)).thenReturn(null);
        FindUidVo vo = new FindUidVo();  // Create a mocked FindUidVo
        when(mapper.selFindUid(dto)).thenReturn(vo);
        vo.setUid("hoho");
        vo.setIuser(5);
        // Test
        vo = service.getFindUid(dto);
        verify(mapper).selFindUid(any());
        log.info("id:{}",vo.getUid());
        assertEquals(vo.getUid(),"hoho");
        assertEquals(vo.getIuser(),5);
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
