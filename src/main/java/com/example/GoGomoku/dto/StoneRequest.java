package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.Color;
import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.Stone;

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
        Integer y,
        Long gameId,
        String sessionId
) {
    public Stone toStoneEntity(Game game,Color color, int newTurn) {
        return Stone.builder()
                .game(game)
                .x(this.x)
                .y(this.y)
                .color(color)
                .turn(newTurn)
                .sessionId(this.sessionId)
                .createdAt(Instant.now())
                .build();
    }
}
