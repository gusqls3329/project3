package com.team5.projrental.mypage.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyBoardListVo {
    private int iboard;
    private String title;
    private String contents; // 앞의 20자 정도만 자르고, 그뒤에 ... 를 붙혀서 주자 (문자열 자르기, 문자열 더하기)
    private int view;
    private int CommentCount;
    private String createdAt;
    private int istatus; // 삭제됨or게시됨or제제됨
}
