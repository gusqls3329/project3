package com.team5.projrental.board;


import com.team5.projrental.entities.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //List<Board> findByAllBoardOrderByIboardDesc(Board board, Pageable pageable);

}
