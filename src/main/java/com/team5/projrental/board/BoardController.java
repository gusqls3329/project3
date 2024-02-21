package com.team5.projrental.board;


import com.team5.projrental.board.model.*;
import com.team5.projrental.common.model.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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


    /*@Operation(summary = "게시글 등록", description = "게시판에 게시글 등록")
    @Parameters(value = {
            @Parameter(name = "title", description = "제목"),
            @Parameter(name = "contents", description = "내용")})
    @PostMapping
    public ResVo postBoard(@RequestPart(required = false) List<MultipartFile> storedPic, @RequestPart BoardInsDto dto) {
        //return service.postBoard(storedPic, dto);
        return null;
    }*/

    @Validated
    @GetMapping
    public List<BoardListSelVo> getBoardList(@PageableDefault(page =1, size = 12) Pageable pageable) {
        return service.getBoardList(pageable);
    }


    @GetMapping("{iboard}")
    public BoardSelVo getBoard(@PathVariable Integer iboard) {
        return null;
    }

    @PutMapping
    public ResVo putBoard(@RequestPart(required = false) List<MultipartFile> storedPic, BoardPutDto dto) {
        return null;
    }

    @DeleteMapping("{iboard}")
    public ResVo delUserBoard(@PathVariable BoardDelDto dto) {
        return null;
    }

    @GetMapping("/like/{iboard}")
    public ResVo toggleLike(@PathVariable Integer iboard) {
        return null;
    }






}
