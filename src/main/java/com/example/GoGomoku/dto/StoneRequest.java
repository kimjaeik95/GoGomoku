package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.Color;
import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.Stone;
import jakarta.servlet.http.HttpSession;

import java.time.Instant;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : StoneRequest
 * author         : JAEIK
 * date           : 2/1/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/25       JAEIK       최초 생성
 */
public record StoneRequest(
        Integer x,
        Integer y
) {
    public Stone toStoneEntity(Game game,Color color, int newTurn, String sessionId) {
        return Stone.builder()
                .game(game)
                .x(x)
                .y(y)
                .color(color)
                .turn(newTurn)
                .sessionId(sessionId)
                .createdAt(Instant.now())
                .build();
    }
}
