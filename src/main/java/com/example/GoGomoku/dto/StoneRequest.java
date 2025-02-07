package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.Color;
import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.Stone;

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
        String color,
        String sessionId
) {
    public Stone toStoneEntity(Game game, int newTurn) {
        return Stone.builder()
                .game(game)
                .x(x)
                .y(y)
                .color(Color.valueOf(color))
                .turn(newTurn)
                .sessionId(sessionId)
                .build();
    }
}
