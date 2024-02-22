package com.team5.projrental.board;


import com.team5.projrental.board.model.*;
import com.team5.projrental.common.model.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService service;


   @Operation(summary = "게시글 등록", description = "게시판에 게시글 등록")
    @Parameters(value = {
            @Parameter(name = "title", description = "제목"),
            @Parameter(name = "contents", description = "내용")})
    @PostMapping
    public ResVo postBoard(@RequestPart(required = false) List<MultipartFile> storedPic, @RequestPart @Validated BoardInsDto dto) {
        //return service.postBoard(storedPic, dto);
        return null;
    }

    @Operation(summary = "전체 게시글 목록", description = "게시판 목록")
    @Parameters(value = {
            @Parameter(name = "page", description = "min:1"),
            @Parameter(name = "size", description = "페이징처리 할 게시글 갯수"),
            @Parameter(name = "sort", description = "변경 할 필요 없음")})
    @Validated
    @GetMapping
    public List<BoardListSelVo> getBoardList(BoardListSelDto dto, @Min(1)int page) {
        return null;
    }


    @Operation(summary = "게시글 입장", description = "특정 게시글 입장")
    @Parameters(value = {
            @Parameter(name = "iboard", description = "입장 할 게시글pk")})
    @GetMapping("{iboard}")
    public BoardSelVo getBoard(@PathVariable int iboard) {
        return null;
    }

    @Operation(summary = "게시글 수정", description = "특정 게시글 수정")
    @Parameters(value = {
            @Parameter(name = "iboard", description = "수정 할 게시글pk"),
            @Parameter(name = "title", description = "수정 할 게시글 제목"),
            @Parameter(name = "contents", description = "수정 할 게시글 내용"),
            @Parameter(name = "storedPic", description = "수정할 게시글 사진")})
    @PutMapping
    public ResVo putBoard(@RequestPart(required = false) List<MultipartFile> storedPic, @RequestPart @Validated BoardPutDto dto) {
        return null;
    }


    @Operation(summary = "게시글 삭제", description = "내가 쓴 게시글 삭제(숨김)")
    @Parameters(value = {
            @Parameter(name = "iboard", description = "삭제 할 게시글pk")})
    @DeleteMapping("{iboard}")
    public ResVo delUserBoard(@PathVariable BoardDelDto dto) {
        return null;
    }


    @Operation(summary = "게시글 좋아요 처리", description = "게시글 좋아요 토글")
    @Parameters(value = {
            @Parameter(name = "iboard", description = "좋아요 처리 할 게시판pk")})
    @GetMapping("/like/{iboard}")
    public ResVo toggleLike(@PathVariable int iboard) {
        return null;
    }






}
