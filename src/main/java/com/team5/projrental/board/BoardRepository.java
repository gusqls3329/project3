package com.team5.projrental.board;


import com.team5.projrental.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
