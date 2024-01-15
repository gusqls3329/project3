package com.team5.projrental.user;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.security.AuthenticationFacade;
import com.team5.projrental.security.JwtTokenProvider;
import com.team5.projrental.security.SecurityUserDetails;
import com.team5.projrental.security.model.SecurityPrincipal;
import com.team5.projrental.user.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityProperties securityProperties;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;

    public int postSignup(UserSignupDto dto) {
        String hashedPw = passwordEncoder.encode(dto.getUpw());

        dto.setUpw(hashedPw);
        dto.setY(40);
        dto.setX(40);

        int result = mapper.insUser(dto);
        log.debug("dto : {}", dto);
        if(result == 1) {
            return Const.SUCCESS;
        }
        return Const.FAIL;
    }


    public SigninVo postSignin(HttpServletResponse res, SigninDto dto) {
        UserEntity entity = mapper.selSignin(dto);

        if (entity == null) {
            return SigninVo.builder().result(Const.LOGIN_NO_UID).build();
        } else if (!passwordEncoder.matches(dto.getUpw(),entity.getUpw())){
            return SigninVo.builder().result(Const.LOGIN_DIFF_UPW).build();
        }

        SecurityPrincipal principal = SecurityPrincipal.builder().iuser(entity.getIuser()).build();
        String at = jwtTokenProvider.generateAccessToken(principal);
        String rt = jwtTokenProvider.generateRefreshToken(principal);

        int rtCookieMaxAge = (int)(securityProperties.getJwt().getRefreshTokenExpiry() / 1000);
        cookieUtils.deleteCookie( res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        return SigninVo.builder()
                .result(String.valueOf(Const.SUCCESS))
                .iuser(entity.getIuser())
                .auth(entity.getAuth())
                .firebaseToken(entity.getFirebaseToken())
                .accessToken(at)
                .build();
    }

    public int getSignOut(HttpServletResponse res){
       cookieUtils.deleteCookie(res,"rt");
        return Const.SUCCESS;
    }

    public SigninVo getRefrechToken(HttpServletRequest req){
        Cookie cookie = cookieUtils.getCookie(req,"rt");
        String token = cookie.getValue();
        if(!jwtTokenProvider.isValidatedToken(token)){
            return SigninVo.builder()
                    .result(String.valueOf(Const.FAIL))
                    .accessToken(null)
                    .build();
        }
        SecurityUserDetails UserDetails = (SecurityUserDetails)jwtTokenProvider.getUserDetailsFromToken(token);
        SecurityPrincipal Principal = UserDetails.getSecurityPrincipal();
        String at = jwtTokenProvider.generateAccessToken(Principal);

        return  SigninVo.builder()
                .result(String.valueOf(Const.SUCCESS))
                .accessToken(at).build();
    }

    public int patchUserFirebaseToken(UserFirebaseTokenPatchDto dto) { //FirebaseToken을 발급 : Firebase방식 : 메시지를 보낼때 ip대신 고유값(Firebase)을 가지고 있는사람에게 메시지 전달
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        int result = mapper.updUserFirebaseToken(dto);
        if(result == 1) {
            return Const.SUCCESS;
        }
        return Const.FAIL;
    }

    public FindUidVo getFindUid(FindUidDto phone) {
        FindUidVo vo = mapper.selFindUid(phone);
        log.info("id:{}", vo);
        return vo;
    }

    public int getFindUpw(FindUpwDto dto) {
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        int result = mapper.upFindUpw(dto);
        if(result == 1) {
            return Const.SUCCESS;
        }
            return Const.FAIL;
    }

    public int putUser(ChangeUserDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);

        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        int result =  mapper.changeUser(dto);
        if(result == 1) {
            return Const.SUCCESS;
        }
        return Const.FAIL;
    }

    public int patchUser(DelUserDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);

        SigninDto inDto = new SigninDto();
        inDto.setUid(dto.getUid());
        inDto.setUpw(dto.getUpw());
        UserEntity entity = mapper.selSignin(inDto);
        String hashedPw = entity.getUpw();
        boolean checkPw = BCrypt.checkpw(dto.getUpw(), hashedPw);
        if (checkPw == true) {
            List<SeldelUserPayDto> payDtos = mapper.seldelUserPay(entity.getIuser());
            for (SeldelUserPayDto list : payDtos) {
                mapper.delUserProPic(list.getIproduct());
                mapper.delUserPorc2(list.getIproduct());
                mapper.delUserPorc(list.getIuser());
                mapper.delUpUserPay(list.getIuser());
            }
        }
        int result = mapper.delUser(dto);
        if(result == 1) {
            return Const.SUCCESS;
        }
        return Const.FAIL;
    }

    public SelUserVo getUSer(int iuser) {
        if(iuser == 0){
            int loginUserPk = authenticationFacade.getLoginUserPk();
            iuser = loginUserPk;
            return mapper.selUser(iuser);
        }
        return mapper.selUser(iuser);
    }
}

