package com.team5.projrental.chat;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.chat.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@RequestMapping("/api/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;


    @Operation(summary = "로그인한 유저의 모든채팅방 개수출력", description = "로그인한 유저의 모든 채팅방 개수 출력" +
            "pageable: 페이지"
    )
    @GetMapping("/{iuser}/count")
    public ResVo getChatCount(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return null;
    }


    @Validated
    @GetMapping
    @Operation(summary = "대화중인 리스트 출력", description = "대화중인 채팅 리스트 출력")
    @Parameters(value = {
            @Parameter(name = "page", description = "page당 노출되는 채팅 방 리스트 10개")})
    public List<ChatSelVo> getChatAll(ChatSelDto dto ,@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return service.getChatAll(dto, pageable);
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
    public ResVo postChatMsg(@Validated @RequestBody ChatMsgInsDto dto) {
        return service.postChatMsg(dto);
    }

    @Validated
    @GetMapping("/room/{ichat}")
    @Operation(summary = "채팅방 입장", description = "채팅방 입장시 모든 내용 출력")
    @Parameters(value = {
            @Parameter(name = "page", description = "페이지"),
            @Parameter(name = "ichat", description = "채팅방 번호")})
    public List<ChatMsgSelVo> getMessageAll(@PageableDefault(page = 1, size = 10)Pageable pageable, long ichat){

        return null;
    }

    @Validated
    @DeleteMapping("/{ichat}/{iuser}")
    @Operation(summary = "채팅방 삭제(숨김)", description = "실행시 로그인 유저의 채팅 숨김 처리되도록 설계함")
    @Parameters(value = {
            @Parameter(name = "ichat", description = "로그인 유저가 선택한 ichat 삭제(실제로는 숨김처리)")})
    public ResVo delChat(ChatMsgDelDto dto) {
        return service.chatDelMsg(dto);
    }


}
