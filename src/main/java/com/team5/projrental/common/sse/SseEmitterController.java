package com.team5.projrental.common.sse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/sse")
public class SseEmitterController {

    private final SseEmitterService sseEmitterService;
    @Operation(summary = "SSE 연결 요청 (로그인직후 요청 필)",
            description = "Accept: text/event-stream<br>" +
                    "event-source-polyfill 을 사용해서 헤더 'Authorization' 에 지속적으로 토큰을 담아 보내야함.")
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() {
        return sseEmitterService.connect();
    }




}
