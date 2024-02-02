package com.team5.projrental.user;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.exception.BadAddressInfoException;
import com.team5.projrental.common.exception.BadDivInformationException;
import com.team5.projrental.common.exception.BadWordException;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.exception.base.*;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.model.ResVo;
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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.team5.projrental.common.exception.ErrorCode.*;
import static com.team5.projrental.common.exception.ErrorMessage.BAD_NICK_EX_MESSAGE;

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

        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getNick(), dto.getRestAddr(), dto.getCompNm() == null ? "" : dto.getCompNm());

        String hashedPw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(hashedPw);
        // 대구 달서구 용산1동 -> x: xxx.xxxxx y: xx.xxxxx address_name: 대구 달서구 용산1동
        if (dto.getCompNm() != null && dto.getCompCode() != 0) {
            dto.setIauth(2);
            if (dto.getCompCode() < 1000000000 || dto.getCompCode() > 9999999999L) {
                throw new BadInformationException(ILLEGAL_RANGE_EX_MESSAGE);
            }

        }
        if ((dto.getCompCode() == 0L && dto.getCompNm() != null) || (dto.getCompCode() != 0L && dto.getCompNm() == null)) {
            throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        }
        Addrs addrs = axisGenerator.getAxis(dto.getAddr());
        CommonUtils.ifAnyNullThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
                addrs, addrs.getAddress_name(), addrs.getX(), addrs.getY());
        dto.setX(Double.parseDouble(addrs.getX()));
        dto.setY(Double.parseDouble(addrs.getY()));
        dto.setAddr(addrs.getAddress_name());

        int result = mapper.insUser(dto);
        if (result == 1) {
            if (dto.getPic() != null) {
                myFileUtils.delFolderTrigger(Const.CATEGORY_USER + "/" + dto.getIuser());
                try {
                    String savedPicFileNm = String.valueOf(
                            myFileUtils.savePic(dto.getPic(), Const.CATEGORY_USER,
                                    String.valueOf(dto.getIuser())));
                    ChangeUserDto picdto = new ChangeUserDto();
                    picdto.setIuser(dto.getIuser());
                    picdto.setChPic(savedPicFileNm);
                    mapper.changeUser(picdto);
                } catch (FileNotContainsDotException e) {
                    throw new BadInformationException(BAD_PIC_EX_MESSAGE);
                }
            }
            if (dto.getCompNm() != null && dto.getCompCode() != 0) {
                UserSignUpComDto comDto = new UserSignUpComDto();
                comDto.setCompCode(dto.getCompCode());
                comDto.setCompNm(dto.getCompNm());
                comDto.setIuser(dto.getIuser());
                int aa = mapper.insCom(comDto);
                if (aa != 1) {
                    throw new BadInformationException(BAD_INFO_EX_MESSAGE);
                }
                int auth = authenticationFacade.getLoginUserAuth();
                return auth;

            }
            if (dto.getCompNm() == null && dto.getCompCode() == 0) {
                int auth = authenticationFacade.getLoginUserAuth();
                return auth;
            }
        }
        throw new BadInformationException(BAD_INFO_EX_MESSAGE);
    }


    public SigninVo postSignin(HttpServletResponse res, SigninDto dto) {
        UserEntity entity = mapper.selSignin(dto);
        if (entity == null) {
            throw new NoSuchDataException(NO_SUCH_ID_EX_MESSAGE);
        } else if (!passwordEncoder.matches(dto.getUpw(), entity.getUpw())) {
            throw new NoSuchDataException(NO_SUCH_PASSWORD_EX_MESSAGE);
        }

        SecurityPrincipal principal = SecurityPrincipal.builder()
                .iuser(entity.getIuser())
                .iauth(entity.getIauth()).build();
        String at = jwtTokenProvider.generateAccessToken(principal);
        String rt = jwtTokenProvider.generateRefreshToken(principal);
        if (res != null) {
            int rtCookieMaxAge = (int) (securityProperties.getJwt().getRefreshTokenExpiry() / 1000);
            cookieUtils.deleteCookie(res, "rt");
            cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);
        }
        return SigninVo.builder()
                .result(String.valueOf(Const.SUCCESS))
                .iauth(entity.getIauth())
                .iuser(entity.getIuser())
                .auth(entity.getAuth())
                .firebaseToken(entity.getFirebaseToken())
                .accessToken(at)
                .build();
    }

    public ResVo patchToken(UserFirebaseTokenPatchDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        return new ResVo(mapper.patchToken(dto));
    }

    public int getSignOut(HttpServletResponse res) {
        try {
            cookieUtils.deleteCookie(res, "rt");
        } catch (NullPointerException e) {
            throw new BadInformationException(AUTHENTICATION_FAIL_EX_MESSAGE);
        }
        return 1;
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

    public ResVo patchUserFirebaseToken(UserFirebaseTokenPatchDto dto) { //FirebaseToken을 발급 : Firebase방식 : 메시지를 보낼때 ip대신 고유값(Firebase)을 가지고 있는사람에게 메시지 전달
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        int result = mapper.updUserFirebaseToken(dto);
        if (result == 1) {
            return new ResVo(Const.SUCCESS);
        }
        throw new BadInformationException(AUTHENTICATION_FAIL_EX_MESSAGE);
    }

    public FindUidVo getFindUid(FindUidDto phone) {
        FindUidVo vo = mapper.selFindUid(phone);
        //vo.setIauth(authenticationFacade.getLoginUserAuth());
        if (vo == null) {
            throw new BadInformationException(NO_SUCH_USER_EX_MESSAGE);
        }

        vo.setUid(vo.getUid().substring(0, 4) + "*".repeat(vo.getUid().substring(4).length()));
        return vo;
    }

    public int getFindUpw(FindUpwDto dto) {
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);

        int result = mapper.upFindUpw(dto);
        FindUidDto fDto = new FindUidDto();
        fDto.setPhone(dto.getPhone());
        FindUidVo fVo = mapper.selFindUid(fDto);

        if (result == 1) {
            //int auth = authenticationFacade.getLoginUserAuth();
            int auth = fVo.getIauth();
            return auth;
        }
        throw new BadInformationException(NO_SUCH_USER_EX_MESSAGE);
    }

    public int putUser(ChangeUserDto dto, MultipartFile pic) {
        if (dto == null && pic == null) throw new BadInformationException(CAN_NOT_BLANK_EX_MESSAGE);
        if (dto == null) dto = new ChangeUserDto();
        int loginUserAuth = authenticationFacade.getLoginUserAuth();
        if (loginUserAuth == 1) {
            dto.setCompCode(0);
            dto.setCompNm(null);
        }
        if(loginUserAuth == 2){
            if (dto.getCompCode() !=0&& dto.getCompCode() < 1000000000 || dto.getCompCode() > 9999999999L) {
                throw new BadInformationException(ILLEGAL_RANGE_EX_MESSAGE);
            }

        }

        if (pic != null) {
            dto.setPic(pic);
        }

        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getNick() == null ? "" : dto.getNick(),
                dto.getCompNm() == null ? "" : dto.getCompNm(),
                dto.getRestAddr() == null ? "" : dto.getRestAddr());

        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);

        if (checkNickOrId(1, dto.getNick()) == null) throw new BadInformationException(BAD_INFO_EX_MESSAGE);


        if (dto.getAddr() != null) {
            Addrs addrs = axisGenerator.getAxis(dto.getAddr());

            CommonUtils.ifAnyNullThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
                    addrs, addrs.getAddress_name(), addrs.getX(), addrs.getY());
            dto.setX(Double.parseDouble(addrs.getX()));
            dto.setY(Double.parseDouble(addrs.getY()));
            dto.setAddr(addrs.getAddress_name());
        }

        String path = Const.CATEGORY_USER + "/" + dto.getIuser();
        myFileUtils.delFolderTrigger(path);
        if (pic != null) {
            try {
                String savedPicFileNm = String.valueOf(
                        myFileUtils.savePic(dto.getPic(), Const.CATEGORY_USER,
                                String.valueOf(dto.getIuser())));
                dto.setChPic(savedPicFileNm);
            } catch (FileNotContainsDotException e) {
                throw new BadInformationException(BAD_PIC_EX_MESSAGE);
            }
        }
        if (dto.getUpw() != null) {
            String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
            dto.setUpw(hashedPw);
        }
        int result = 1;
        int compResult = 1;
        if (CommonUtils.ifAllNullReturnFalse(
                dto.getNick(), dto.getChPic(), dto.getUpw(),
                dto.getPhone(), dto.getAddr(),
                dto.getRestAddr(), dto.getEmail())) {
            result = mapper.changeUser(dto);
        }
        if (dto.getCompCode() > 0 || dto.getCompNm() != null) {
            compResult = mapper.changeCompInfo(new CompInfoDto(dto.getCompCode(), dto.getCompNm()));
        }
        if (result == 1 && compResult == 1) {
            int auth = authenticationFacade.getLoginUserAuth();
            return auth;
        }
        throw new BadDateInfoException(AUTHENTICATION_FAIL_EX_MESSAGE);
    }

    public int patchUser(DelUserDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);


        SigninDto inDto = new SigninDto();
        inDto.setUid(dto.getUid());
        inDto.setUpw(dto.getUpw());
        UserEntity entity = mapper.selSignin(inDto);

        if (entity == null) {
            throw new NoSuchDataException(NO_SUCH_ID_EX_MESSAGE);
        } else if (!passwordEncoder.matches(dto.getUpw(), entity.getUpw())) {
            throw new NoSuchDataException(NO_SUCH_PASSWORD_EX_MESSAGE);
        }
        Integer check = mapper.selpatchUser(entity.getIuser());
        if (loginUserPk == entity.getIuser()) {
            String hashedPw = entity.getUpw();
            boolean checkPw = passwordEncoder.matches(dto.getUpw(), hashedPw);
            if (checkPw) {
                if (check != null && check != 0) {
                    throw new IllegalException(CAN_NOT_DEL_USER_EX_MESSAGE);
                } else {
                    // 채팅 개수 가져오기 && 채팅 삭제
                    if (!mapper.getUserChatCount(loginUserPk).equals(mapper.delUserChat(loginUserPk))) {
                        throw new IllegalException(CAN_NOT_DEL_USER_EX_MESSAGE);
                    }

                    List<SeldelUserPayDto> payDtos = mapper.seldelUserPay(entity.getIuser());

                    List<Integer> iproducts = new ArrayList<>();
                    List<Integer> iusers = new ArrayList<>();

                    for (SeldelUserPayDto list : payDtos) {
                        iproducts.add(list.getIproduct());
                        iusers.add(list.getIuser());
                    }
                    iusers.add(loginUserPk);

                    if(!iproducts.isEmpty()) {
                        mapper.delUserProPic(iproducts);
                    }
                        mapper.delLike(iusers);
                        mapper.delRev(iusers);

                }

                int result = mapper.delUser(dto);
                if (result == 1) {
                    return Const.SUCCESS;
                }
                throw new WrapRuntimeException(ILLEGAL_EX_MESSAGE);
            } else {
                throw new BadDateInfoException(AUTHENTICATION_FAIL_EX_MESSAGE);
            }
        }
        throw new BadDateInfoException(AUTHENTICATION_FAIL_EX_MESSAGE);
    }

    public SelUserVo getUser(Integer iuser) {
        boolean checker = iuser == null || iuser == 0;
        Integer actionIuser = checker ?  authenticationFacade.getLoginUserPk(): iuser;

        SelUserVo vo = mapper.selUser(actionIuser);

        if (vo.getIauth() == 2) {
            CompInfoDto compInf = mapper.getCompInf(actionIuser);
            CommonUtils.ifAllNullThrow(BadInformationException.class, BAD_INFO_EX_MESSAGE,
                    compInf);
            vo.setCompCode(compInf.getCompCode());
            vo.setCompNm((compInf.getCompNm()));
        }
        return vo;
    }

    public ResVo checkUserInfo(UserCheckInfoDto dto) { // div = 1 || nick = "..."
        return new ResVo(checkNickOrId(dto.getDiv(), dto.getDiv() == 1 ? dto.getNick() : dto.getUid()));
    }


    private Integer checkNickOrId(Integer div, String obj) {
        Integer result = null;
        if (div == 1) {
            result = mapper.checkUserNick(obj);
            if (result > 0) {
                throw new BadInformationException(BAD_NICK_EX_MESSAGE);
            }
        }
        if (div == 2) {
            result = mapper.checkUserUid(obj);
            if (result > 0) {
                throw new BadInformationException(BAD_ID_EX_MESSAGE);
            }
        }
        return result;
    }


}

