package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.Color;
import com.example.GoGomoku.entity.GameStatus;
import com.fasterxml.jackson.annotation.JsonInclude;


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
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Color stoneColor,
        String message

) {
}
