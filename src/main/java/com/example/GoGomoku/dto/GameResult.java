package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.GameStatus;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : GameResult
 * author         : JAEIK
 * date           : 3/19/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/19/25       JAEIK       최초 생성
 */
public record GameResult(
        GameStatus gameStatus,
        String message
) {
}
