package com.team5.projrental.user;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.security.JwtTokenProvider;
import com.team5.projrental.common.security.model.SecurityPrincipal;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.user.model.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    private KakaoAxisGenerator axisGenerator;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    private HttpServletResponse res;
    @Autowired
    private UserService service;


    @Test
    void postSignup() {
        when(mapper.insUser(any())).thenReturn(1);
        FindUidVo fDto = new FindUidVo();
        fDto.setIauth(1);

        when(mapper.selFindUid(any())).thenReturn(fDto);
        when(authenticationFacade.getLoginUserAuth()).thenReturn(1);
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

        log.info("entity:{}", entity);
        log.info("dto:{}", dto.getUpw());

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
        log.info("id:{}", vo.getUid());
        assertEquals(vo.getUid(), "hoho");
        assertEquals(vo.getIuser(), 5);
    }

    @Test
    void getFindUpw() {
        FindUpwDto dto = new FindUpwDto();
        dto.setUpw("12121212");
        dto.setUid("dongdong12");
        dto.setPhone("010-7777-6666");

        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        when(mapper.upFindUpw(dto)).thenReturn(1);
        FindUidVo vo = new FindUidVo();
        vo.setIauth(12);
        when(mapper.selFindUid(any())).thenReturn(vo);
        int result = service.getFindUpw(dto);
        verify(mapper).upFindUpw(dto);
        assertEquals(result, 12);
    }

/*    @Test
    void putUser() {
        ChangeUserDto dto = new ChangeUserDto();
        dto.setPhone("010-3333-3333");
        dto.setNick("변경");
        dto.setIuser(5);
        when(mapper.changeUser(dto)).thenReturn(1);

        int result = service.putUser(dto);
        verify(mapper).changeUser(dto);
        assertEquals(result, 1);
    }*/

    @Test
    void patchUser() {

        DelUserDto dto = new DelUserDto();
        UserEntity entity = new UserEntity();
        dto.setUid("12341234");
        dto.setUpw("12341234");
        entity.setUid(dto.getUid());
        entity.setUpw(dto.getUpw());
        entity.setIuser(1);
        entity.setFirebaseToken("123");
        dto.setIuser(1);
        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        when(mapper.selSignin(any())).thenReturn(entity);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(mapper.selpatchUser(anyInt())).thenReturn(0);
        when(mapper.delUser(any())).thenReturn(1);
        int result = service.patchUser(dto);

        verify(mapper).selSignin(any());
        verify(authenticationFacade).getLoginUserPk();
       // verify(mapper).selpatchUser(any());
        verify(mapper).delUser(any());
        assertEquals(result, 1);
    }

    @Test
    void getUser() {
        SelUserVo vo = new SelUserVo();
        when(mapper.selUser(1)).thenReturn(vo);
        vo.setPhone("테스트");
        vo.setNick("테스트");
        vo.setX(1);
        vo.setY(2);
        vo.setAddr("테스트");
        vo.setEmail("테스트");
        vo.setAddr("테스트");
        vo.setStoredPic("테스트");
        vo = service.getUser(1);
        assertEquals(vo.getEmail(), null);
        assertEquals(vo.getPhone(), null);
    }

    @Test
    void checkUserInfo() {

    }
}
