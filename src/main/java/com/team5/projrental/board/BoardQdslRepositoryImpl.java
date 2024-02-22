package com.team5.projrental.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.board.model.BoardListSelDto;
import com.team5.projrental.board.model.BoardListSelVo;
import com.team5.projrental.board.model.BoardSelDto;
import com.team5.projrental.entities.Board;
import com.team5.projrental.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.team5.projrental.entities.QBoard.board;

@Slf4j
@RequiredArgsConstructor
public class BoardQdslRepositoryImpl implements BoardQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    /*@Override
    public List<BoardListSelVo> selboardAll(BoardListSelDto dto, Pageable pageable) {
        JPAQuery<Board> jpaQuery = jpaQueryFactory.select(board)
                .from(board)
                .join(board.users).fetchJoin();

        return null;
    }*/

    /*@Override
    public List<BoardListSelVo> selboardAll(User user, Pageable pageable) {
        List<Board> boardList = jpaQueryFactory.select(board)
                .from(board)
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<BoardListSelVo> list = boardList.stream().map(item ->
                BoardListSelVo.builder()
                        .iboard(item.getId().intValue())
                        //.totalBoardCount()
                        .title(item.getTitle())
                        .view(item.getView().intValue())
                        //.createdAt(item.g)
                        .nick(user.getNick())
                        //.isLiked()
                        .build())
                .collect(Collectors.toList());



        return null;
    }*/

}


