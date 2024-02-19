package com.team5.projrental.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListSelVo {
    private Integer totalBoardCount;

    private String nick;
    private String userPic;

    private Integer isLiked;

    private Integer iboard;
    private String title;
    private Integer view;
    private String createdAt;
}
