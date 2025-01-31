package com.example.GoGomoku.controller;

import com.example.GoGomoku.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/board")
    public String newBoard(Model model) {
        int[][] board = boardService.createBoard();
        model.addAttribute("board", board);
        return "/board/board";
    }
}
