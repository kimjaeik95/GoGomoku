package com.example.GoGomoku.websocket;

import com.example.GoGomoku.dto.GameIdSessionIdResult;
import com.example.GoGomoku.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    @MessageMapping("/start/game")
    public void startGame(SimpMessageHeaderAccessor headerAccessor) {
        Long gameId = boardService.createGame();
        String sessionId = headerAccessor.getSessionId();
        log.info("gameId: {}, sessionId : {}" , gameId, sessionId);
        simpMessagingTemplate.convertAndSend("/topic/game/start", new GameIdSessionIdResult(gameId, sessionId));
    }
}
