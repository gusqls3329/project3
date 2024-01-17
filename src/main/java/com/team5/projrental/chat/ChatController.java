package com.team5.projrental.chat;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.chat.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;


    @GetMapping
    @Operation(summary = "대화중인 리스트 출력", description = "대화중인 채팅 리스트 출력")
    @Parameters(value = {
            @Parameter(name = "page", description = "page당 노출되는 채팅 방 리스트 20개")})
    public List<ChatSelVo> getChatAll(ChatSelDto dto) {
        return service.getChatAll(dto);
    }

    @PostMapping
    @Operation(summary = "채팅방 생성", description = "빈 채팅방 생성 후 참여 유저 입력")
    @Parameters(value = {
            @Parameter(name = "iproduct", description = "제품 PK"),
            @Parameter(name = "otherPersonIuser", description = "상대유저 PK")})
    public ChatSelVo PostChat(@RequestBody ChatInsDto dto) {

        return service.postChat(dto);
    }

    @PostMapping("/msg")
    @Operation(summary = "메세지 작성", description = "채팅메세지 전송")
    @Parameters(value = {
            @Parameter(name = "page", description = "페이지"),
            @Parameter(name = "msg", description = "보낼 메세지")})
    public ResVo postChatMsg(@RequestBody @Validated ChatMsgInsDto dto) {
        return service.postChatMsg(dto);
    }


    @GetMapping("/msg")
    @Operation(summary = "채팅방 입장", description = "채팅방 입장시 모든 내용 출력")
    @Parameters(value = {
            @Parameter(name = "page", description = "페이지"),
            @Parameter(name = "ichat", description = "채팅방 번호")})
    public List<ChatMsgSelVo> getChatMsgAll(ChatMsgSelDto dto){
        log.info("dto : {}", dto);
        return service.getMsgAll(dto);
    }

    @DeleteMapping("/msg")
    @Operation(summary = "채팅방 삭제(숨김)", description = "실행시 로그인 유저의 채팅 숨김 처리되도록 설계함")
    @Parameters(value = {
            @Parameter(name = "ichat", description = "로그인 유저가 선택한 ichat 삭제(실제로는 숨김처리)")})
    public ResVo delChatMsg(ChatMsgDelDto dto) {
        return service.chatDelMsg(dto);
    }

}
