package com.team5.projrental.user;

import com.team5.projrental.common.exception.BadInformationException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo postSignup(UserSignupDto dto) {
        String salt = BCrypt.gensalt();
        String hashedPw = BCrypt.hashpw(dto.getUpw(), salt);

        dto.setUpw(hashedPw);
        dto.setY(40);
        dto.setX(40);

        int result = mapper.insUser(dto);
        log.debug("dto : {}", dto);
        if(result == 0) {
            return new ResVo(0);
        }
        return new ResVo(1);
    }


    public SigninVo postSignin(SigninDto dto) {
        SigninVo vo = mapper.selSignin(dto);

        if (vo.getUid() == null) {
            vo.setResult("아이디 로그인 실패");
            return vo;
        }

        String hashedPw = vo.getUpw();
        boolean checkPw = BCrypt.checkpw(dto.getUpw(), hashedPw);
        if (checkPw == false) {
            vo.setResult("비밀번호 틀림");
        } else {
            vo.setResult("로그인 성공");
        }
        log.info("vo: {}", vo);
        return vo;
    }

    public FindUidVo getFindUid(FindUidDto phone) {
        FindUidVo vo = mapper.selFindUid(phone);
        log.info("id:{}", vo);
        return vo;
    }

    public int getFindUpw(FindUpwDto dto) {
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        int a = mapper.upFindUpw(dto);
        return a;
    }

    public int putUser(ChangeUserDto dto) {
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        return mapper.changeUser(dto);
    }

    public int patchUser(DelUserDto dto) {
        SigninDto inDto = new SigninDto();
        inDto.setUid(dto.getUid());
        inDto.setUpw(dto.getUpw());
        SigninVo vo = mapper.selSignin(inDto);
        String hashedPw = vo.getUpw();
        boolean checkPw = BCrypt.checkpw(dto.getUpw(), hashedPw);
        if (checkPw == true) {
            List<SeldelUserPayDto> payDtos = mapper.seldelUserPay(vo.getIuser());
            for (SeldelUserPayDto list : payDtos) {
                mapper.delUserProPic(list.getIproduct());
                mapper.delUserPorc2(list.getIproduct());
                mapper.delUserPorc(list.getIuser());
                mapper.delUpUserPay(list.getIuser());
            }
        }
        return mapper.delUser(dto);
    }

    public SelUserVo getUSer(int iuser) {
        return mapper.selUser(iuser);
    }
}

