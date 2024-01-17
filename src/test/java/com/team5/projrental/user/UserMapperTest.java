package com.team5.projrental.user;

import com.team5.projrental.user.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    void insSignup() {
        UserSignupDto dto = new UserSignupDto();
        dto.setUid("test");
        dto.setUpw("1212");
        dto.setNick("연습중");
        dto.setAddr("111");
        dto.setEmail("test@naver.com");
//        dto.setPic("aa.jpg");
        dto.setRestAddr("11-1");
        dto.setPhone("010-1212-1212");

        int result = mapper.insUser(dto);
        assertEquals(1,result);
        assertEquals(true,dto.getIuser()>0);

        SigninDto indto = new SigninDto();
        indto.setUpw(dto.getUpw());
        indto.setUid(dto.getUid());
        UserEntity vo =  mapper.selSignin(indto);
        assertEquals(vo.getIuser(),dto.getIuser());
        log.info("iuser:{}",vo.getIuser());
        SelUserVo selVo = mapper.selUser(vo.getIuser());
        log.info("selVo","dto",selVo.getNick(),dto.getNick());
        assertEquals(selVo.getNick(),dto.getNick());
        assertEquals(selVo.getPhone(),dto.getPhone());
        assertEquals(selVo.getEmail(),dto.getEmail());
    }

    @Test
    void selSignin() {
        SigninDto dto = new SigninDto();
        dto.setUid("happy");
        dto.setUpw("1212");

        UserEntity vo = mapper.selSignin(dto);
        assertEquals(vo.getIuser(),2);
        assertEquals(vo.getUid(),dto.getUid());

        dto.setUid("maru");
        dto.setUpw("1212");
        UserEntity vo2 = mapper.selSignin(dto);
        assertEquals(vo2.getIuser(),1);
        assertEquals(vo2.getUid(),dto.getUid());
    }

    @Test
    void selFindUid() {
        FindUidDto dto = new FindUidDto();
        dto.setPhone("010-4563-9872");

        FindUidVo vo = mapper.selFindUid(dto);
        assertEquals(vo.getUid(),"happy");
        assertEquals(vo.getIuser(),2);
        SelUserVo selvo = mapper.selUser(vo.getIuser());
        assertEquals(selvo.getPhone(),dto.getPhone());
    }

    @Test
    void upFindUpw() {
        FindUpwDto dto = new FindUpwDto();
        dto.setUpw("1212");
        dto.setPhone("010-9876-5432");
        dto.setUid("maru");

        int a = mapper.upFindUpw(dto);
        assertEquals(a, 1);

        SigninDto indto = new SigninDto();
        indto.setUpw(dto.getUpw());
        indto.setUid(dto.getUid());
        UserEntity vo = mapper.selSignin(indto);
        assertEquals(vo.getIuser(),1);
        SelUserVo selvo = mapper.selUser(vo.getIuser());
        assertEquals(selvo.getPhone(),dto.getPhone());


        dto.setUpw("12121");
        dto.setPhone("010-9999-8888");
        dto.setUid("eve");
        int a1 = mapper.upFindUpw(dto);
        log.info("a1: {}",a1);
        assertEquals(a1, 1);

        SigninDto indto1 = new SigninDto();
        indto1.setUpw(dto.getUpw());
        indto1.setUid(dto.getUid());
        UserEntity vo1 = mapper.selSignin(indto1);
        assertEquals(vo1.getIuser(),3);
        SelUserVo selvo1 = mapper.selUser(vo1.getIuser());
        assertEquals(selvo1.getPhone(),dto.getPhone());
    }

    @Test
    void changeUser() {
        ChangeUserDto dto = new ChangeUserDto();
        dto.setEmail("mm@naver.com");
//        dto.setPic("변경함");
        dto.setNick("닉닉");
        dto.setPhone("010-0101-0101");
        dto.setIuser(3);
        int changeUser = mapper.changeUser(dto);
        log.info("changeUser:{}"+changeUser);
        assertEquals(1,changeUser);

        SelUserVo vo = mapper.selUser(dto.getIuser());
        assertEquals(vo.getNick(),dto.getNick());
        assertEquals(vo.getEmail(),dto.getEmail());
        assertEquals(vo.getPhone(),dto.getPhone());
    }

    @Test
    void delUser() {
    }

    @Test
    void seldelUserPay() {
    }

    @Test
    void delUpUserPay() {
    }

    @Test
    void delUserProPic() {
    }

    @Test
    void delUserPorc2() {
    }

    @Test
    void delUserPorc() {
    }

    @Test
    void selUser() {
        SelUserVo vo = mapper.selUser(1);
        assertEquals(vo.getEmail(),"maru@naver.com");
        assertEquals(vo.getNick(),"마루야노올자");
        assertEquals(vo.getPhone(),"010-9876-5432");
        assertEquals(vo.getX(),120);
        assertEquals(vo.getY(),100);

        SelUserVo vo2 = mapper.selUser(2);
        assertEquals(vo2.getEmail(),"happy@naver.com");
        assertEquals(vo2.getNick(),"인사이드아웃");
        assertEquals(vo2.getPhone(),"010-4563-9872");
        assertEquals(vo2.getX(),13);
        assertEquals(vo2.getY(),40);
    }


}
