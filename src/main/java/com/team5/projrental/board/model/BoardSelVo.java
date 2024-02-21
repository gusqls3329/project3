package com.team5.projrental.board.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSelVo {
    private String nick;
    private String userPic;

    private Integer isLiked;

    private Integer iboard;
    private String title;
    private String contents;
    private Integer view;
    private LocalDateTime createdAt;
    private List<String> pic;

    private List<BoardCommentSelVo> comments;


}
