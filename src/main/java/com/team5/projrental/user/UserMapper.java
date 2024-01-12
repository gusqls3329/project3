package com.team5.projrental.user;

import com.team5.projrental.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserSignupDto dto);
    SigninVo selSignin(SigninDto dto);
    FindUidVo selFindUid(FindUidDto phone);
    int upFindUpw(FindUpwDto dto);
    int changeUser(ChangeUserDto dto);
    int delUser(DelUserDto dto);
    List<SeldelUserPayDto> seldelUserPay(int iuser);
    int delUpUserPay(int iuser);
    int delUserProPic(int iproduct);
    int delUserPorc2(int iproduct);
    int delUserPorc(int iuser);

    SelUserVo selUser(int iuser);
}
