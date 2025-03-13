package com.example.GoGomoku.controller;

import com.example.GoGomoku.dto.GameRequest;
import com.example.GoGomoku.dto.StoneRequest;
import com.example.GoGomoku.entity.Color;
import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.Stone;
import com.example.GoGomoku.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/show/game")
    public String showGameStart() {
        return "/board/show_game";
    }

    @PostMapping("/create/game")
    public String newGame() {
        boardService.createGame();
        return "redirect:/board/board";
    }

    @GetMapping("/board")
    public String newBoard(Model model) {
        Stone[][] board = boardService.createBoard();
        model.addAttribute("board", board);
        return "/board/board";
    }

    @PostMapping("/board/stone")
    public String saveStone(@RequestBody StoneRequest stoneRequest, @RequestParam("gameId") Long gameId, HttpSession session) {
        boardService.createStone(stoneRequest, gameId, session);
        return "redirect:/board?gameId=" + gameId;
    }
}
