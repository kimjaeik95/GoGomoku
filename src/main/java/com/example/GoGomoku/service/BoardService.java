package com.example.GoGomoku.service;

import org.springframework.stereotype.Service;

/**
 * packageName    : com.example.GoGomoku.service
 * fileName       : BoardService
 * author         : JAEIK
 * date           : 1/31/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/25       JAEIK       최초 생성
 */
@Service
public class BoardService {
    public int[][] createBoard() {
        int size = 15;
        int[][] board = new int[size][size];
        return board;
    }
}
