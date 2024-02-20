package com.team5.projrental.board.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListSelVo {
    private Integer totalBoardCount;

    private String nick;

    private Integer isLiked; // 0: 좋아요 안함 // 1: 좋아요 누름

    private Integer iboard;
    private String title;
    private Integer view;
    private String createdAt;
}
