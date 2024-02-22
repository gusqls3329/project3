package com.team5.projrental.board.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListSelVo {
    private int totalBoardCount;

    private String nick;

    private int isLiked; // 0: 좋아요 안함 // 1: 좋아요 누름

    private int iboard;
    private String title;
    private int view;
    private LocalDateTime createdAt;
}
