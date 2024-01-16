package com.team5.projrental.chat;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.chat.model.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    @GetMapping
    public List<ChatSelVo> getChatAll(ChatSelDto dto) {
        return service.getChatAll(dto);
    }

    @PostMapping
    public ChatSelVo PostChat(@RequestBody ChatInsDto dto) {

        return service.postChat(dto);
    }

    @PostMapping("/msg")
    public ResVo postChatMsg(@RequestBody ChatMsgInsDto dto) {
        return service.postChatMsg(dto);
    }


    @GetMapping("/msg")
    @Operation(summary = "채팅방 입장", description = "채팅방 입장시 모든 내용 출력" +
            "<br>page : 페이지(defoult = 0)" +
            "<br>ichat : 채팅 방번호" +
            "<br>row_count : 페이지당 채팅 수(defoult = 20" +
            "<br>loginedIuser : 로그인한 유저PK")
    public List<ChatMsgSelVo> getChatMsgAll(ChatMsgSelDto dto){
        log.info("dto : {}", dto);
        return service.getMsgAll(dto);
    }

    @DeleteMapping("/msg")
    @Operation(summary = "채팅방 삭제", description = "istatus = 0: 채팅 중" + "<br>istatus = 1: 삭제됨(숨김)" +
            "<br>ichat : 채팅 방번호PK, seq : 채팅 메세지 PK, iuser : ")
    public ResVo delChatMsg(ChatMsgDelDto dto) {
        return service.chatDelMsg(dto);
    }

}
