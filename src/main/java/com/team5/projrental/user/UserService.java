package com.team5.projrental.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.exception.base.*;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.exception.thrid.ClientException;
import com.team5.projrental.common.exception.thrid.ServerException;
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
import com.team5.projrental.entities.*;
import com.team5.projrental.entities.embeddable.Address;
import com.team5.projrental.entities.enums.*;
import com.team5.projrental.entities.inheritance.QUsers;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseUser;
import com.team5.projrental.user.model.*;
import com.team5.projrental.user.verification.SignUpVo;
import com.team5.projrental.user.verification.users.TossVerificationRequester;
import com.team5.projrental.user.verification.users.model.VerificationUserInfo;
import com.team5.projrental.user.verification.users.model.check.CheckResponseVo;
import com.team5.projrental.user.verification.users.model.ready.VerificationReadyVo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.team5.projrental.common.exception.ErrorCode.*;
import static com.team5.projrental.common.exception.ErrorMessage.BAD_NICK_EX_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityProperties securityProperties;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;
    private final AxisGenerator axisGenerator;
    private final MyFileUtils myFileUtils;
    private final TossVerificationRequester tossVerificationRequester;
    private final JPAQueryFactory queryFactory;

    @Value("${config.activate.on-profile}")
    private String profile;

    public VerificationReadyVo readyVerification(VerificationUserInfo userInfo) {
        return tossVerificationRequester.verificationRequest(userInfo);
    }

    public CheckResponseVo checkVerification(String uuid) {
        return tossVerificationRequester.check(uuid);
    }

    @Transactional
    public int postSignup(UserSignupDto dto) {

        Addrs addrs = axisGenerator.getAxis(dto.getAddr());
        CommonUtils.ifAnyNullThrow(BadAddressInfoException.class, BAD_ADDRESS_INFO_EX_MESSAGE,
                addrs, addrs.getAddress_name(), addrs.getX(), addrs.getY());
        BaseUser baseUser = new BaseUser();
        Address address = Address.builder()
                .addr(dto.getAddr())
                .restAddr(addrs.getAddress_name())
                .x(Double.parseDouble(addrs.getX()))
                .y(Double.parseDouble(addrs.getY()))
                .build();
        baseUser.setAddress(address);
        String hashedPw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(hashedPw);

        String path = Const.CATEGORY_USER + "/" + dto.getIuser();
        myFileUtils.delFolderTrigger(path);

            if (dto.getPic() != null) {
                try {
                    String savedPicFileNm = String.valueOf(
                            myFileUtils.savePic(dto.getPic(), Const.CATEGORY_USER,
                                    String.valueOf(dto.getIuser())));
                    dto.setChPic(savedPicFileNm);
                } catch (FileNotContainsDotException e) {
                    throw new BadInformationException(BAD_PIC_EX_MESSAGE);
                }
            }
            baseUser.setStoredPic(dto.getChPic());
            //baseUser.setStatus(dto.get);
            User user = new User();
            user.setStatus(UserStatus.ACTIVE);
            user.setUid(dto.getUid());
            user.setUpw(hashedPw);
            user.setBaseUser(baseUser);
            user.setNick(dto.getNick());
            user.setProvideType(ProvideType.LOCAL);
            user.setEmail(dto.getEmail());
            user.setPhone(dto.getPhone());
            user.setAuth(Auth.USER);
            userRepository.save(user);

            return Const.SUCCESS;
    }


    public SigninVo postSignin(HttpServletResponse res, SigninDto dto) {
//        UserEntity entity = mapper.selSignin(dto);

        User user = null;

        Users findUsers = usersRepository.findByUid(dto.getUid());
        if (findUsers == null) {
            throw new ClientException(ErrorCode.NO_SUCH_ID_EX_MESSAGE, "아이디가 존재하지 않음");
        }
        String password = "";
        Long iuser = 0L;
        Auth auth = findUsers.getAuth();

        if (auth == Auth.USER) {
            user = (User) findUsers;
            password = user.getUpw();
            iuser = user.getId();
        }

        QUsers users = QUsers.users;
        queryFactory.select(users)
                .from();

        if (user == null) {
            throw new NoSuchDataException(NO_SUCH_ID_EX_MESSAGE);
        } else if (!passwordEncoder.matches(dto.getUpw(), password)) {
            throw new NoSuchDataException(NO_SUCH_PASSWORD_EX_MESSAGE);
        }



        SelSigninVo vo = mapper.selLoginStatus(dto);
        if( UserStatus.HIDE.toString().equals(vo.getUstatus())){
            throw new ClientException(NO_SUCH_USER_HIDE_MESSAGE);
        }
        if(UserStatus.COMPANION.toString().equals(vo.getUstatus()) ){
            throw new ClientException(NO_SUCH_USER_COMPANION_MESSAGE);
        }
        SecurityPrincipal principal = SecurityPrincipal.builder()
                .iuser(iuser)
                .auth(auth.name())
                .build();

        String at = jwtTokenProvider.generateAccessToken(principal);
        String rt = jwtTokenProvider.generateRefreshToken(principal);
        if (res != null) {
            int rtCookieMaxAge = (int) (securityProperties.getJwt().getRefreshTokenExpiry() / 1000);
            cookieUtils.deleteCookie(res, "rt");
            cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);
        }

        return SigninVo.builder()
                .result(String.valueOf(Const.SUCCESS))
                .iuser(iuser)
                .auth(auth.getIauth())
//                .firebaseToken(entity.getFirebaseToken())
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
        Optional<String> optRt = cookieUtils.getCookie(req, "rt").map(Cookie::getValue);
        if (optRt.isEmpty()) {
            return SigninVo.builder()
                    .result(String.valueOf(Const.FAIL))
                    .accessToken(null)
                    .build();
        }
        String token = optRt.get();

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
        Long loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        int result = mapper.updUserFirebaseToken(dto);
        if (result == 1) {
            return new ResVo(Const.SUCCESS);
        }
        throw new BadInformationException(AUTHENTICATION_FAIL_EX_MESSAGE);
    }
    @Transactional
    public FindUidVo getFindUid(String phone) {
        Users users = userRepository.findByUid(phone);
        return FindUidVo.builder().uid(users.getUid()).build();
    }
    @Transactional
    public int getFindUpw(FindUpwDto dto) {
        User findUser = (User) userRepository.findByUid(dto.getUid());
        findUser.setUpw(dto.getUpw());

        return Const.SUCCESS;
    }

/*
    public FindUidVo getFindUid(FindUidDto phone) {
        FindUidVo vo = mapper.selFindUid(phone);

        //vo.setIauth(authenticationFacade.getLoginUserAuth());
        if (vo == null) {
            throw new BadInformationException(NO_SUCH_USER_EX_MESSAGE);
        }

        vo.setUid(vo.getUid().substring(0, 4) + "*".repeat(vo.getUid().substring(4).length()));
        return vo;
    }*/
/*
    public int getFindUpw(FindUpwDto dto) {
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);

        int result = mapper.upFindUpw(dto);

        if (result == 1) {
            return Const.SUCCESS;
        }
        throw new BadInformationException(NO_SUCH_USER_EX_MESSAGE);
    }*/

    public int putUser(ChangeUserDto dto, MultipartFile pic) {
        if (dto == null && pic == null) throw new BadInformationException(CAN_NOT_BLANK_EX_MESSAGE);
        if (dto == null) dto = new ChangeUserDto();
        Auth loginUserAuth = authenticationFacade.getLoginUserAuth();

        if (pic != null) {
            dto.setPic(pic);
        }

        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getNick() == null ? "" : dto.getNick(),
                dto.getRestAddr() == null ? "" : dto.getRestAddr());

        Long loginUserPk = authenticationFacade.getLoginUserPk();
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
        if (CommonUtils.ifAllNullReturnFalse(
                dto.getNick(), dto.getChPic(), dto.getUpw(),
                dto.getPhone(), dto.getAddr(),
                dto.getRestAddr(), dto.getEmail())) {
            result = mapper.changeUser(dto);
        }

        if (result == 1 ) {
            Auth auth = authenticationFacade.getLoginUserAuth();
            return Const.SUCCESS;
        }
        throw new BadDateInfoException(AUTHENTICATION_FAIL_EX_MESSAGE);
    }

    @Transactional
    public int patchUser(DelUserDto dto) {
        Long loginUserPk = authenticationFacade.getLoginUserPk();
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
                    List<Long> iusers = new ArrayList<>();

                    for (SeldelUserPayDto list : payDtos) {
                        iproducts.add(list.getIproduct());
                        iusers.add(list.getIuser());
                    }
                    iusers.add(loginUserPk);

                    if (!iproducts.isEmpty()) {
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

    public SelUserVo getUser(Long iuser) {
        boolean checker = iuser == null || iuser == 0;
        Long actionIuser = checker ? authenticationFacade.getLoginUserPk() : iuser;

        SelUserVo vo = mapper.selUser(actionIuser);

        if(iuser != authenticationFacade.getLoginUserPk()){
            vo.setPhone(null);
            vo.setEmail(null);
        }

        return vo;
    }

    public ResVo checkUserInfo(UserCheckInfoDto dto) { // div = 1 || nick = "..."
        return new ResVo(checkNickOrId(dto.getDiv(), dto.getDiv() == 1 ? dto.getNick() : dto.getUid()));
    }


    private Integer checkNickOrId(Integer div, String obj) {
        Integer result = null;
        if (div == 1) {
            result = mapper.checkUserNickComp(obj);
            Integer result1 = mapper.checkUserNickUser(obj);
            if (result + result1 > 0) {
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

