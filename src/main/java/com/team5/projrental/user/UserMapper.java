package com.team5.projrental.user;

import com.team5.projrental.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserSignupDto dto);
    int insCom(UserSignUpComDto dto);
    UserEntity selSignin(SigninDto dto);
    int updUserFirebaseToken(UserFirebaseTokenPatchDto dto);

    FindUidVo selFindUid(FindUidDto phone);
    int upFindUpw(FindUpwDto dto);
    int changeUser(ChangeUserDto dto);
    int delUser(DelUserDto dto);
    List<SeldelUserPayDto> seldelUserPay(int iuser);
    int delUpUserPay(List<Integer> iuser);
    int delUserProPic(List<Integer> iproduct);
    int delUserPorc2(List<Integer> iproduct);
    int delUserPorc(List<Integer> iuser);

    SelUserVo selUser(int iuser);
    Integer selpatchUser(int iuser);

    Integer checkUserUid(String dto);
    Integer checkUserNick(String dto);

    // 채팅 삭제 관련
    Integer getUserChatCount(Integer iuser);

    Integer delUserChat(Integer iuser);

    int patchToken(UserFirebaseTokenPatchDto dto);

    CompInfoDto getCompInf(int iuser);

    int changeCompInfo(CompInfoDto dto);
}
