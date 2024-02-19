package com.team5.projrental.board;


import com.team5.projrental.board.model.BoardInsDto;
import com.team5.projrental.board.model.BoardPicInsDto;
import com.team5.projrental.common.exception.BadWordException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.entities.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.team5.projrental.common.exception.ErrorCode.BAD_WORD_EX_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    /*private final BoardRepository boardRepository;
    private final AuthenticationFacade authenticationFacade;
    public ResVo postBoard(BoardInsDto dto) {
        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getTitle(), dto.getContents());

        Board board = boardRepository.getReferenceById((long)authenticationFacade.getLoginUserPk());
        board.setTitle(dto.getTitle());
        board.setContents(dto.getContents());
        *//*if(storedPic != null && !storedPic.isEmpty()) {
            BoardPicInsDto boardPicInsDto = new BoardPicInsDto();
            //사진
        }*//*
        boardRepository.save(board);
        return new ResVo(1);
    }*/
}
