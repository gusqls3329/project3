package com.team5.projrental.board;


import com.team5.projrental.entities.Board;
import com.team5.projrental.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    /*@EntityGraph(attributePaths = {"user"})
    List<Board> findAllByUserOrderByIboardDesc(User user, Pageable pageable);*/
}
