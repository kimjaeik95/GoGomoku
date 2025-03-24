package com.example.GoGomoku.controller;

import com.example.GoGomoku.dto.GameResult;
import com.example.GoGomoku.dto.StoneRequest;
import com.example.GoGomoku.entity.Stone;
import com.example.GoGomoku.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/game/start/view")
    public String showGameStart() {
        return "/board/show_start_game";
    }

    @PostMapping("/game/start")
    public String newGame(HttpSession session) {
        Long gameId = boardService.createGame();
        session.setAttribute("gameId", gameId);
        return "redirect:/api/game/board/view";
    }

    @GetMapping("/game/board/view")
    public String newBoard(@RequestParam("gameId") Long gameId, Model model) {
        model.addAttribute("gameId", gameId);

        Stone[][] board = boardService.createBoard();
        model.addAttribute("board", board);
        return "/board/board";
    }

//    @PostMapping("/board/stone")
//    public ResponseEntity<?> saveStone(@RequestBody StoneRequest stoneRequest, HttpSession session) {
//        Long gameId = (Long) session.getAttribute("gameId");
//        GameResult gameResult = boardService.createStoneGameResult(stoneRequest, gameId, session);
//        return ResponseEntity.ok(gameResult);
//    }
}
