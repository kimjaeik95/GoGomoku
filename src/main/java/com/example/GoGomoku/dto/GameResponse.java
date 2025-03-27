package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.GameStatus;


import java.time.Instant;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : GameResponse
 * author         : JAEIK
 * date           : 2/1/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/25       JAEIK       최초 생성
 */
public record GameResponse(
        Long id,
        Instant startTime,
        Instant endTime,
        String winnerSessionId,
        GameStatus status
) {
}
