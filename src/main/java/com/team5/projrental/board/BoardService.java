package com.team5.projrental.board;


import com.team5.projrental.board.model.BoardInsDto;
import com.team5.projrental.board.model.BoardListSelDto;
import com.team5.projrental.board.model.BoardListSelVo;
import com.team5.projrental.board.model.BoardPicInsDto;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.BadWordException;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.exception.thrid.ClientException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.entities.Board;
import com.team5.projrental.entities.BoardPic;
import com.team5.projrental.entities.User;
import com.team5.projrental.entities.inheritance.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.team5.projrental.common.exception.ErrorCode.BAD_PIC_EX_MESSAGE;
import static com.team5.projrental.common.exception.ErrorCode.BAD_WORD_EX_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    /*private final BoardRepository boardRepository;
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;

    public ResVo postBoard(List<MultipartFile> storedPics, BoardInsDto dto) {
        CommonUtils.ifContainsBadWordThrow(BadWordException.class, BAD_WORD_EX_MESSAGE,
                dto.getTitle(), dto.getContents());

        dto.setStoredPic(storedPics);

        Board board = boardRepository.getReferenceById((long)authenticationFacade.getLoginUserPk());
        board.setTitle(dto.getTitle());
        board.setContents(dto.getContents());
        boardRepository.save(board);

        BoardPicInsDto boardPicInsDto = new BoardPicInsDto();
        boardPicInsDto.setIboard(board.getId().intValue());
        if(storedPics != null && !storedPics.isEmpty()) {
            for (MultipartFile file : dto.getStoredPic()) {
                try {
                    myFileUtils.delFolderTrigger("board" + "/" + boardPicInsDto.getIboard());
                    String picName = String.valueOf(myFileUtils.savePic(file, "board", String.valueOf(boardPicInsDto.getIboard())));
                } catch (FileNotContainsDotException e) {
                    throw new ClientException(BAD_PIC_EX_MESSAGE);
                }
                List<BoardPic> boardPicList = boardPicInsDto.getStoredPic()
                        .stream()
                        .map(item -> BoardPic.builder()
                                .storedPic(item)
                                .board(board)
                                .build()).collect(Collectors.toList());
                board.getBoardPicList().addAll(boardPicList);
                return new ResVo(boardPicInsDto.getIboard());
            }
        }
        return new ResVo(boardPicInsDto.getIboard());
    }

    public List<BoardListSelVo> getBoardList(Pageable pageable) {
        List<Board> boardList = null;
        User user = new User();
        return null;



        *//*BoardListSelDto dto = new BoardListSelDto();
        dto.setLoginedIuser(authenticationFacade.getLoginUserPk());
        if(dto.getLoginedIuser() > 0){
            boardList = boardRepository.findAllByUserOrderByIboardDesc(user, pageable);
        }*//*

        *//*return boardList.stream().map(item -> {
        Users users = new User();
        users.setId((long)authenticationFacade.getLoginUserPk());
        int isFav = boardRepository.findById(users.getId()).isPresent() ? 1 :0;



        })*//*
        *//*return BoardListSelVo.builder()
                .nick(user.getNick())
                .iboard()
                .build()*//*
    }*/
}
