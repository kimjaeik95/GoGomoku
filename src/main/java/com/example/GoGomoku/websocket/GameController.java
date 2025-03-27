package com.example.GoGomoku.websocket;

import com.example.GoGomoku.dto.StoneGameUpdateResponse;
import com.example.GoGomoku.dto.GameIdDto;
import com.example.GoGomoku.dto.GameResult;
import com.example.GoGomoku.dto.StoneRequest;
import com.example.GoGomoku.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

    @MessageMapping("/start/game")  // 자동으로 /pub (prefix)가 붙어서 동작
    public void startGame() {
        Long gameId = boardService.createGame();
        log.info("gameId : {}" , gameId);

        // "/topic/start/game" 구독하는 클라이언트들에게 메시지를 전송하는 역할
        simpMessagingTemplate.convertAndSend("/topic/start/game", new GameIdDto(gameId));
    }

    @MessageMapping("/game/stone/save")
    public void stoneSave(StoneRequest stoneRequest) {
        Long gameId = stoneRequest.gameId();
        GameResult gameResult = boardService.createStoneGameResult(stoneRequest);
        StoneGameUpdateResponse response = new StoneGameUpdateResponse(stoneRequest, gameResult);
        simpMessagingTemplate.convertAndSend("/topic/start/game/" + gameId, response);
    }
}
