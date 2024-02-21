package com.team5.projrental.board.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentSelVo {
    private Integer iuser;
    private String nick;
    private String userPic;
    private String comment;
    private LocalDateTime createdAt;
}
