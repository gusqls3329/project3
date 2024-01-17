package com.team5.projrental.user;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.exception.BadAddressInfoException;
import com.team5.projrental.common.exception.RestApiException;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.exception.user.BadIdInfoException;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.utils.AxisGenerator;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.security.JwtTokenProvider;
import com.team5.projrental.common.security.SecurityUserDetails;
import com.team5.projrental.common.security.model.SecurityPrincipal;
import com.team5.projrental.common.utils.MyFileUtils;
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

import static com.team5.projrental.common.exception.ErrorCode.*;

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
    private final AxisGenerator axisGenerator;
    private final MyFileUtils myFileUtils;

    public int postSignup(UserSignupDto dto) {

        String hashedPw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(hashedPw);
        // 대구 달서구 용산1동 -> x: xxx.xxxxx y: xx.xxxxx address_name: 대구 달서구 용산1동

        Addrs addrs = axisGenerator.getAxis(dto.getAddr());
        CommonUtils.ifAnyNullThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
                addrs, addrs.getAddress_name(), addrs.getX(), addrs.getY());
        dto.setX(Double.parseDouble(addrs.getX()));
        dto.setY(Double.parseDouble(addrs.getY()));
        dto.setAddr(addrs.getAddress_name());

        int result = mapper.insUser(dto);

        log.debug("dto : {}", dto);

        if (result == 1) {
            if (dto.getPic() != null ) {
                log.info("사진 :{}", dto.getPic());
                String path = "/user/";
                myFileUtils.delFolderTrigger(path);
                try {
                    String savedPicFileNm = String.valueOf(
                            myFileUtils.savePic(dto.getPic(), Const.CATEGORY_USER,
                                    String.valueOf(dto.getIuser())));
                    ChangeUserDto picdto = new ChangeUserDto();
                    picdto.setIuser(dto.getIuser());
                    picdto.setChPic(savedPicFileNm);
                    mapper.changeUser(picdto);
                } catch (FileNotContainsDotException e) {
                    throw new RuntimeException(e);
                }


            }

            return Const.SUCCESS;
        }
            return Const.FAIL;


    }


    public SigninVo postSignin(HttpServletResponse res, SigninDto dto) {
        UserEntity entity = mapper.selSignin(dto);

        if (entity == null) {
            // throw new BadInformationException("존재하지 않는 아이디 입니다.");
        } else if (!passwordEncoder.matches(dto.getUpw(), entity.getUpw())) {
            throw new RuntimeException("비밀번호를 잘못 입력하셨습니다.");
        }

        SecurityPrincipal principal = SecurityPrincipal.builder().iuser(entity.getIuser()).build();
        String at = jwtTokenProvider.generateAccessToken(principal);
        String rt = jwtTokenProvider.generateRefreshToken(principal);

        int rtCookieMaxAge = (int) (securityProperties.getJwt().getRefreshTokenExpiry() / 1000);
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        return SigninVo.builder()
                .result(String.valueOf(Const.SUCCESS))
                .iuser(entity.getIuser())
                .auth(entity.getAuth())
                .firebaseToken(entity.getFirebaseToken())
                .accessToken(at)
                .build();
    }

    public int getSignOut(HttpServletResponse res) {
        cookieUtils.deleteCookie(res, "rt");
        return Const.SUCCESS;
    }

    public SigninVo getRefrechToken(HttpServletRequest req) {
        Cookie cookie = cookieUtils.getCookie(req, "rt");
        String token = cookie.getValue();
        if (!jwtTokenProvider.isValidatedToken(token)) {
            return SigninVo.builder()
                    .result(String.valueOf(Const.FAIL))
                    .accessToken(null)
                    .build();
        }
        SecurityUserDetails UserDetails = (SecurityUserDetails) jwtTokenProvider.getUserDetailsFromToken(token);
        SecurityPrincipal Principal = UserDetails.getSecurityPrincipal();
        String at = jwtTokenProvider.generateAccessToken(Principal);

        return SigninVo.builder()
                .result(String.valueOf(Const.SUCCESS))
                .accessToken(at).build();
    }

    public int patchUserFirebaseToken(UserFirebaseTokenPatchDto dto) { //FirebaseToken을 발급 : Firebase방식 : 메시지를 보낼때 ip대신 고유값(Firebase)을 가지고 있는사람에게 메시지 전달
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        int result = mapper.updUserFirebaseToken(dto);
        if (result == 1) {
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
        if (result == 1) {
            return Const.SUCCESS;
        }
        return Const.FAIL;
    }

    public int putUser(ChangeUserDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);


        Addrs addrs = axisGenerator.getAxis(dto.getAddr());
        CommonUtils.ifAnyNullThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
                addrs, addrs.getAddress_name(), addrs.getX(), addrs.getY());
        dto.setX(Double.parseDouble(addrs.getX()));
        dto.setY(Double.parseDouble(addrs.getY()));
        dto.setAddr(addrs.getAddress_name());


        String path = "/user/" + dto.getIuser();
        myFileUtils.delFolderTrigger(path);
        try {
            String savedPicFileNm = String.valueOf(
                    myFileUtils.savePic(dto.getPic(), Const.CATEGORY_USER,
                            String.valueOf(dto.getIuser())));
            dto.setChPic(savedPicFileNm);
        } catch (FileNotContainsDotException e) {
            throw new RuntimeException(e);
        }


        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        int result = mapper.changeUser(dto);
        if (result == 1) {
            return Const.SUCCESS;
        }
        return Const.FAIL;
    }

    public int patchUser(DelUserDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        log.info("loginUserPk :{}", loginUserPk);
        SigninDto inDto = new SigninDto();
        inDto.setUid(dto.getUid());
        inDto.setUpw(dto.getUpw());
        UserEntity entity = mapper.selSignin(inDto);

        if (entity == null) {
            return Integer.parseInt(NO_SUCH_ID_EX_MESSAGE.getMessage());
        } else if (!passwordEncoder.matches(dto.getUpw(), entity.getUpw())) {
            return Integer.parseInt(NO_SUCH_PASSWORD_EX_MESSAGE.getMessage());
        }

        if (loginUserPk == entity.getIuser()) {
            String hashedPw = entity.getUpw();
            boolean checkPw = BCrypt.checkpw(dto.getUpw(), hashedPw);
            if (checkPw) {
                List<SeldelUserPayDto> payDtos = mapper.seldelUserPay(entity.getIuser());
                List<Integer> iproduct = mapper.patchUser(dto.getIuser());

                for (SeldelUserPayDto list : payDtos) {
                    for (Integer iproductsss : iproduct) {
                        if (list.getIproduct() != iproductsss) {
                            return Const.FAIL;
                        }
                    }
                    mapper.delUserProPic(list.getIproduct());
                    mapper.delUserPorc2(list.getIproduct());
                    mapper.delUserPorc(list.getIuser());
                    mapper.delUpUserPay(list.getIuser());
                }
            }
            int result = mapper.delUser(dto);
            if (result == 1) {
                return Const.SUCCESS;
            }
            return Const.FAIL;
        } else {
            return Const.FAIL;
        }
    }

    public SelUserVo getUser(Integer iuser) {
        if (iuser == null || iuser == 0) { //내가 아닐때.
            int loginUserPk = authenticationFacade.getLoginUserPk();
            SelUserVo vo1 = mapper.selUser(loginUserPk);
            return vo1;
        }
        SelUserVo vo2 = mapper.selUser(iuser);
        vo2.setEmail(null);
        vo2.setPhone(null);
        return vo2;
    }

}

