package com.team5.projrental.board.comment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentReposity boardCommentReposity;
}
