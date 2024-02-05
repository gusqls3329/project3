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
        dto.setUid("qwqwqw11");
        dto.setUpw("12121212");

        UserEntity vo = mapper.selSignin(dto);
        assertEquals(vo.getIuser(),1);
        assertEquals(vo.getUid(),dto.getUid());

        dto.setUid("qwqwqw22");
        dto.setUpw("12121212");
        UserEntity vo2 = mapper.selSignin(dto);
        assertEquals(vo2.getIuser(),2);
        assertEquals(vo2.getUid(),dto.getUid());
    }

    @Test
    void selFindUid() {
        FindUidDto dto = new FindUidDto();
        dto.setPhone("010-1111-1111");

        FindUidVo vo = mapper.selFindUid(dto);
        assertEquals(vo.getUid(),"qwqwqw11");
        assertEquals(vo.getIuser(),1);
        SelUserVo selvo = mapper.selUser(vo.getIuser());
        assertEquals(selvo.getPhone(),dto.getPhone());
    }

    @Test
    void upFindUpw() {
        FindUpwDto dto = new FindUpwDto();
        dto.setUpw("12121212");
        dto.setPhone("010-1111-1111");
        dto.setUid("qwqwqw11");

        int a = mapper.upFindUpw(dto);
        assertEquals(a, 1);

        SigninDto indto = new SigninDto();
        indto.setUpw(dto.getUpw());
        indto.setUid(dto.getUid());
        UserEntity vo = mapper.selSignin(indto);
        assertEquals(vo.getIuser(),1);
        SelUserVo selvo = mapper.selUser(vo.getIuser());
        assertEquals(selvo.getPhone(),dto.getPhone());
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
        assertEquals(vo.getEmail(),"loll1111@naver.com");
        assertEquals(vo.getNick(),"바보현빈");
        assertEquals(vo.getPhone(),"010-1111-1111");

    }


    @Test
    void selpatchUser() {
        Integer result = mapper.selpatchUser(1);
        assertEquals(result,7);

        Integer result2 = mapper.selpatchUser(2);
        assertEquals(result2,2);
    }

    @Test
    void checkUserUid() {
        Integer result = mapper.checkUserUid("12341234");
        assertEquals(result,0);

        Integer result2 = mapper.checkUserUid("qwqwqw11");
        assertEquals(result2,1);
    }

    @Test
    void checkUserNick() {
        Integer result = mapper.checkUserNick("감자8");
        assertEquals(result,1);

        Integer result2 = mapper.checkUserNick("집");
        assertEquals(result2,0);
    }
}
