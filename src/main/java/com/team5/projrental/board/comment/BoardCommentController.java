package com.team5.projrental.board.comment;


import com.team5.projrental.board.comment.model.BoardCommentPatchDto;
import com.team5.projrental.board.comment.model.BoardCommentPostDto;
import com.team5.projrental.common.model.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/comment")
public class BoardCommentController {
    private final BoardCommentService service;

    @Operation(summary = "댓글 등록", description = "게시글에 댓글 등록")
    @Parameters(value = {
            @Parameter(name = "comment", description = "댓글 내용")})
    @PostMapping
    public ResVo postComment(@RequestBody BoardCommentPostDto dto) {
        return null;
    }


    @Operation(summary = "댓글 수정", description = "수정할 댓글 등록")
    @Parameters(value = {
            @Parameter(name = "comment", description = "댓글 내용")})
    @PatchMapping
    public ResVo patchComment(@RequestBody BoardCommentPatchDto dto) {
        return null;
    }

    @Operation(summary = "댓글 삭제", description = "내가 쓴 댓글 삭제(숨김:status를 2로 변경/default:1)")
    @Parameters(value = {
            @Parameter(name = "iboardComment", description = "삭제할 댓글pk")})
    @DeleteMapping("{iboardComment}")
    public ResVo delComment(@PathVariable int iboardComment) {
        return null;
    }
}
