package com.team5.projrental.user;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.user.model.*;
import com.team5.projrental.user.verification.SignUpVo;
import com.team5.projrental.user.verification.users.model.VerificationUserInfo;
import com.team5.projrental.user.verification.users.model.check.CheckResponseVo;
import com.team5.projrental.user.verification.users.model.ready.VerificationReadyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/verification")
    @Operation(summary = "본인인증 요청", description = "본인 요청하기")
    public VerificationReadyVo readyVerification(@RequestBody VerificationUserInfo userInfo){
        return service.readyVerification(userInfo);
    }

    @GetMapping("/verification")
    @Operation(summary = "본인인증 결과 확인", description = "본인인증 수행 햇는지 확인")
    public CheckResponseVo checkVerification(String uuid){
        return service.checkVerification(uuid);
    }

    @PostMapping("/check")
    @Operation(summary = "중복확인", description = "uid, nick 중복확인")
    @Parameters(value = {
            @Parameter(name="div", description = "div:1 - nick 중복확인 <br>div:2 - uid 중복확인")
            , @Parameter(name="uid", description = "아이디")
            , @Parameter(name="nick", description = "닉네임")
    })
    public ResVo CheckUserInfo(@RequestBody @Validated UserCheckInfoDto dto) {
        return service.checkUserInfo(dto);
    }

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "회원가입", description = "유저 회원가입, 권한이 리턴됨")
    @Parameters(value = {
            @Parameter(name="addr", description = "동/면/읍까지의 주소")
            , @Parameter(name="restAddr", description = "나머지 주소")
            , @Parameter(name="uid", description = "아이디")
            , @Parameter(name="upw", description = "비밀번호")
            , @Parameter(name="nick", description = "닉네임")
            , @Parameter(name="pic", description = "사진")
            , @Parameter(name="phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
            , @Parameter(name="email", description = "이메일 (형식 : aaa@naver.com)")
            , @Parameter(name = "compCode", description = "사업자번호")
            , @Parameter(name = "compCeo", description = "회사 대표이름")
            , @Parameter(name = "staredAt", description = "개업일")
            , @Parameter(name = "compNm", description = "회사이름")
            , @Parameter(name = "isValid", description = "중복체크 ( 완료되었다면 2) ")
            , @Parameter(name = "uuid", description = "???-아무거나 적어줘요")
            , @Parameter(name = "signUpType", description = "회원가입시 유저(1)인지, 기업(2)인지")

    })
    public SignUpVo postSignup(@RequestPart(required = false) MultipartFile pic, @RequestPart @Validated UserSignupDto dto) {
        dto.setPic(pic);
        log.info("dto : {}", dto);
        return service.postSignup(dto);
    }

    @PostMapping
    @Operation(summary = "로그인", description = "유저 로그인(iauth:권한)<br><br>" +
            "로그인 성공시 fireBaseToken 수정부분 수행 해야함.(Patch: /api/user/fcm")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디")
            , @Parameter(name = "upw", description = "비밀번호")
    })
    public SigninVo postSignin(HttpServletResponse res, @RequestBody @Validated SigninDto dto) {
        return service.postSignin(res, dto);
    }

    @Operation(summary = "fireBaseToken 등록", description = "발급받은 해당 유저의 브라우저에 발급된 " +
            "fireBaseToken 을 로그인한 유저에 등록")
    @Parameters(value = {
            @Parameter(name = "firebaseToken", description = "토큰값")
    })
    @PatchMapping("/fcm")
    public ResVo patchToken(UserFirebaseTokenPatchDto dto) {

        return service.patchToken(dto);
    }

    @PostMapping("/signout")
    public ResVo getSignOut(HttpServletResponse res){
        return new ResVo(service.getSignOut(res));
    }

    @GetMapping("/refrech-token")
    public SigninVo getRefrechToken(HttpServletRequest req){
        return service.getRefrechToken(req);
    }

    @PatchMapping("/firebase-token")
    public ResVo patchUserFirebaseToken(@RequestBody UserFirebaseTokenPatchDto dto) {
        return service.patchUserFirebaseToken(dto);
    }

    @Operation(summary = "아이디 찾기", description = "유저 아이디 찾기(iauth:권한) ")
    @Parameters(value = {
            @Parameter(name = "phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
    })
    @PostMapping("/id")
    public FindUidVo getFindUid(@RequestBody @Validated FindUidDto phone) {
        return service.getFindUid(phone);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 찾기 불가능, 비밀번호 수정, 권한이 리턴됨")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디")
            , @Parameter(name = "phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
    })
    @PatchMapping("/pw")
    public ResVo getFindUpw(@RequestBody @Validated FindUpwDto dto) {
        return new ResVo(service.getFindUpw(dto));
    }

    @Operation(summary = "회원정보 수정", description = "닉네임, 프로필 사진, 비밀번호 수정, 위치 수정")
    @Parameters(value = {
            @Parameter(name = "addr", description = "동/면/읍까지의 주소")
            , @Parameter(name = "restAddr", description = "나머지 주소")
            , @Parameter(name = "upw", description = "비밀번호")
            , @Parameter(name = "nick", description = "닉네임")
            , @Parameter(name = "pic", description = "사진")
            , @Parameter(name = "phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
            , @Parameter(name = "email", description = "이메일 (형식 : xxx@xxx.xxx)")
            , @Parameter(name = "compCode", description = "사업자번호")
            , @Parameter(name = "compCeo", description = "회사 대표이름")
            , @Parameter(name = "staredAt", description = "개업일")
            , @Parameter(name = "compNm", description = "회사이름")

    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResVo putUser(@RequestPart(required = false) @Validated ChangeUserDto dto,
                         @RequestParam(required = false) MultipartFile pic) {
        return new ResVo(service.putUser(dto, pic));
    }

    @Operation(summary = "회원탈퇴", description = "유저 삭제 : 유저정보, 상품, 결제정보 등 삭제")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디")
            , @Parameter(name = "upw", description = "비밀번호")
            , @Parameter(name = "phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
    })
    @PatchMapping
    public ResVo patchUser(@RequestBody @Validated DelUserDto dto) {
        return new ResVo(service.patchUser(dto));
    }

    @Operation(summary = "유저 정보 조회", description = "유저 개인 정보 조회, (iauth:권한)")
    @Parameters(value = {
            @Parameter(name = "tar", description = "유저 Pk값")
    })
    @Validated
    @GetMapping
    public SelUserVo getUSer(@RequestParam(value = "tar", required = false) @Min(1) Integer iuser) {
        return service.getUser(iuser);
    }
}
