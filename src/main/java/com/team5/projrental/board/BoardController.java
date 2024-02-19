package com.team5.projrental.board;


import com.team5.projrental.board.model.BoardInsDto;
import com.team5.projrental.common.model.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService service;


    @PostMapping
    public ResVo postBoard(@RequestPart(required = false) List<MultipartFile> storedPic, @RequestPart BoardInsDto dto) {
        return service.postBoard(storedPic, dto);
    }
}
