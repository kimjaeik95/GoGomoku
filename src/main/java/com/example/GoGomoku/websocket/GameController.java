package com.example.GoGomoku.websocket;

import com.example.GoGomoku.dto.*;
import com.example.GoGomoku.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.*;

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
    private final Queue<String> waitingQueue = new LinkedList<>();

    @MessageMapping("/start/game")  // 자동으로 /pub (prefix)가 붙어서 동작
    public void startGame(Principal principal) {
       String playerId = principal.getName();

        if (!waitingQueue.contains(playerId)) {
            waitingQueue.add(playerId);
        }

        simpMessagingTemplate.convertAndSend("/topic/start/game", new GameIdDto(null, "대기중", playerId, waitingQueue.size()));

        if (waitingQueue.size() == 2) {
            List<String> playerInGame = new ArrayList<>();
            playerInGame.add(waitingQueue.poll());
            playerInGame.add(waitingQueue.poll());

            Long gameId = boardService.createGame();
            log.info("gameId : {}", gameId);


            for (String username : playerInGame) {
                log.info("Sending to user: " + username + " with gameId: " + gameId);
                simpMessagingTemplate.convertAndSendToUser(username,  //1:1 유니캐스트
                         "/queue/start/game",
                        new GameIdDto(gameId, "게임시작", playerId, 2));
                log.info("convertAndSendToUser:{}", "/user/" + username + "/queue/start/game");
            }
        }
    }

    @MessageMapping("/game/stone/save")
    public void stoneSave(StoneRequest stoneRequest) {
        Long gameId = stoneRequest.gameId();
        GameResult gameResult = boardService.createStoneGameResult(stoneRequest);
        StoneGameUpdateResponse response = new StoneGameUpdateResponse(stoneRequest, gameResult);
        simpMessagingTemplate.convertAndSend("/topic/start/game/" + gameId, response);
    }
}
