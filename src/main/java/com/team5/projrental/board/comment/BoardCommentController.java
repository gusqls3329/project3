package com.team5.projrental.board.comment;


import com.team5.projrental.common.model.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/comment")
public class BoardCommentController {
    private final BoardCommentService service;

    @PostMapping
    public ResVo postComment() {
        return null;
    }

    @PatchMapping
    public ResVo patchComment() {
        return null;
    }

    @DeleteMapping("{iboardComment}")
    public ResVo delComment(@PathVariable Integer iboardComment) {
        return null;
    }
}
