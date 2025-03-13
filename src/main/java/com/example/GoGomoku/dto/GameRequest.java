package com.example.GoGomoku.dto;

import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.GameStatus;
import lombok.Builder;

import java.time.Instant;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : GameRequest
 * author         : JAEIK
 * date           : 3/13/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/25       JAEIK       최초 생성
 */

public record GameRequest(
        Long id,
        Instant startTime,
        GameStatus status
) {
    public GameRequest {

    }

    /*
     파라미터가 있는 생성자를 작성할 때,
     기본 생성자가 이미 자동으로 정의되어 있기 때문에,
     명시적으로 기본 생성자를 작성할 필요가 없습니다.
     */
    public GameRequest(Game game) {
        this(game.getId(), game.getStartTime(), game.getStatus());
    }
}