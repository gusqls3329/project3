package com.team5.projrental.board.comment;


import com.team5.projrental.entities.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentReposity extends JpaRepository<BoardComment, Long> {
}
