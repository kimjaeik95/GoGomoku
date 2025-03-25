package com.example.GoGomoku.websocket;

import com.example.GoGomoku.dto.GameIdDto;
import com.example.GoGomoku.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * packageName    : com.example.GoGomoku.websocket
 * fileName       : GameController
 * author         : JAEIK
 * date           : 3/24/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/24/25       JAEIK       최초 생성
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class GameController {
    private final BoardService boardService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/start/game")  // 자동으로 /pub (prefix)가 붙어서 동작
    public void startGame() {
        Long gameId = boardService.createGame();
        log.info("gameId : {}" , gameId);

        // "/topic/start/game" 구독하는 클라이언트들에게 메시지를 전송하는 역할
        simpMessagingTemplate.convertAndSend("/topic/start/game", new GameIdDto(gameId));
    }
}
