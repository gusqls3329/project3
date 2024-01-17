package com.team5.projrental.user;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/check")
    @Operation(summary = "", description = "")
    public ResVo CheckUserInfo(@RequestBody @Validated UserCheckInfoDto dto) {
        return service.checkUserInfo(dto);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "유저 회원가입")
    @Parameters(value = {
            @Parameter(name="addr", description = "동/면/읍까지의 주소")
            , @Parameter(name="restAddr", description = "나머지 주소")
            , @Parameter(name="uid", description = "아이디")
            , @Parameter(name="upw", description = "비밀번호")
            , @Parameter(name="nick", description = "닉네임")
            , @Parameter(name="pic", description = "사진")
            , @Parameter(name="phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
            , @Parameter(name="email", description = "이메일 (형식 : aaa@naver.com)")
    })
    public ResVo postSignup(@RequestPart(required = false) MultipartFile pic,@RequestPart @Validated UserSignupDto dto) {
        dto.setPic(pic);
        log.info("dto : {}", dto);
        return new ResVo(service.postSignup(dto));
    }

    @PostMapping
    @Operation(summary = "로그인", description = "유저 로그인")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디")
            , @Parameter(name = "upw", description = "비밀번호")
    })
    public SigninVo postSignin(HttpServletResponse res, @RequestBody @Validated SigninDto dto) {
        return service.postSignin(res, dto);
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
        return new ResVo(service.patchUserFirebaseToken(dto));
    }

    @Operation(summary = "아이디 찾기", description = "유저 아이디 찾기")
    @Parameters(value = {
            @Parameter(name = "phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
    })
    @PostMapping("/id")
    public FindUidVo getFindUid(@RequestBody @Validated FindUidDto phone) {
        return service.getFindUid(phone);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 찾기 불가능, 비밀번호 수정")
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
    })
    @PutMapping
    public int putUser(@RequestPart @Validated ChangeUserDto dto) {
        return service.putUser(dto);
    }

    @Operation(summary = "회원탈퇴", description = "유저 삭제 : 유저정보, 상품, 결제정보 등 삭제")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디")
            , @Parameter(name = "upw", description = "비밀번호")
            , @Parameter(name = "phone", description = "휴대폰 번호 (형식 : 010-1111-2222)")
    })
    @PatchMapping
    public int patchUser(@RequestBody @Validated DelUserDto dto) {
        return service.patchUser(dto);
    }

    @Operation(summary = "유저 정보 조회", description = "유저 개인 정보 조회")
    @Parameters(value = {
            @Parameter(name = "iuser", description = "유저 Pk값")
    })

    @Validated
    @GetMapping
    public SelUserVo getUSer(@RequestParam(value = "tar", required = false) @Min(1) Integer iuser) {
        return service.getUser(iuser);
    }
}
