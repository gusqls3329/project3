package com.team5.projrental.user;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    //@PostMapping("/signup")

    @PostMapping
    public SigninVo postSignin(@RequestBody SigninDto dto){
        return service.postSignin(dto);
    }

    @PostMapping("/id")
    public FindUidVo getFindUid(@RequestBody FindUidDto phone){
        return service.getFindUid(phone);
    }

    @PatchMapping("/pw")
    public ResVo getFindUpw(@RequestBody FindUpwDto dto){
        return new ResVo(service.getFindUpw(dto));
    }
    @PutMapping
    public int putUser(@RequestBody ChangeUserDto dto){
        return service.putUser(dto);
    }
    @PatchMapping
    public int patchUser(@RequestBody DelUserDto dto){
        return service.patchUser(dto);
    }

}
