package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.Color;

import java.time.Instant;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : StoneResponse
 * author         : JAEIK
 * date           : 2/1/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/25       JAEIK       최초 생성
 */
public record StoneResponse(
        Long id,
        String gameId,
        Integer x,
        Integer y,
        Color color,
        Integer turn,
        String sessionId,
        Instant createAt
) {
}
