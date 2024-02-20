package com.team5.projrental.board;


import com.team5.projrental.board.model.BoardInsDto;
import com.team5.projrental.board.model.BoardListSelVo;
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
    /*private final BoardService service;


    @Operation(summary = "게시글 등록", description = "게시판에 게시글 등록")
    @Parameters(value = {
            @Parameter(name = "title", description = "제목"),
            @Parameter(name = "contents", description = "내용")})
    @PostMapping
    public ResVo postBoard(@RequestPart(required = false) List<MultipartFile> storedPic, @RequestPart BoardInsDto dto) {
        return service.postBoard(storedPic, dto);
    }

    @Validated
    @GetMapping
    public List<BoardListSelVo> getBoardList(@PageableDefault(page =1, size = 12) Pageable pageable) {
        return service.getBoardList(pageable);
    }*/

}
