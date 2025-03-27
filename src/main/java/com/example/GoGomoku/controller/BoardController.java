package com.example.GoGomoku.controller;

import com.example.GoGomoku.dto.GameIdDto;
import com.example.GoGomoku.dto.GameResult;
import com.example.GoGomoku.dto.StoneRequest;
import com.example.GoGomoku.entity.Stone;
import com.example.GoGomoku.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * packageName    : com.example.GoGomoku.controller
 * fileName       : BoardController
 * author         : JAEIK
 * date           : 1/31/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/25       JAEIK       최초 생성
 */
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/game/start/view")
    public String showGameStart() {
        return "/board/show_start_game";
    }

    @PostMapping("/set/gameid")
    public ResponseEntity<?> setGameId(@RequestBody GameIdDto gameIdDto, HttpSession session) {
        Long gameId = gameIdDto.gameId();
        session.setAttribute("gameId", gameId);
        log.info("gameId saved in session: {}", gameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/game/board/view")
    public String newBoard(HttpSession session, Model model) {
        Long gameId = (Long) session.getAttribute("gameId");
        log.info("Received gameId: {}", gameId);
        model.addAttribute("gameId", gameId);
        log.info("gameId : {}" , gameId);
        Stone[][] board = boardService.createBoard();
        model.addAttribute("board", board);
        return "/board/board";
    }
    // 주석처리한곳은 양방향 통신을 위해 불 필요한 restful API

//    @PostMapping("/game/start")
//    public String newGame(HttpSession session) {
//        Long gameId = boardService.createGame();
//        session.setAttribute("gameId", gameId);
//        return "redirect:/api/game/board/view";
//    }

//    @PostMapping("/board/stone")
//    public ResponseEntity<?> saveStone(@RequestBody StoneRequest stoneRequest, HttpSession session) {
//        Long gameId = (Long) session.getAttribute("gameId");
//        GameResult gameResult = boardService.createStoneGameResult(stoneRequest, gameId, session);
//        return ResponseEntity.ok(gameResult);
//    }
}
