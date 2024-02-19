package com.team5.projrental.board;


import com.team5.projrental.board.model.BoardInsDto;
import com.team5.projrental.board.model.BoardRepository;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.entities.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final AuthenticationFacade authenticationFacade;
    public ResVo postBoard(BoardInsDto dto) {

        Board board = boardRepository.getReferenceById((long)authenticationFacade.getLoginUserPk());
        board.setTitle(dto.getTitle());
        board.setContents(dto.getContents());




        return null;
    }
}
