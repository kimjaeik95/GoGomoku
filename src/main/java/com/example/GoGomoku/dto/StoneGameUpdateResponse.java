package com.example.GoGomoku.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : Game
 * author         : JAEIK
 * date           : 3/26/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/26/25       JAEIK       최초 생성
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class StoneGameUpdateResponse {
    // 웹소켓 양방향 통신을 위한 DTO
    // 게임 진행을 위한 요청 정보 (돌의 위치 등)
    StoneRequest stoneRequest;

    // 게임 결가를 위한 정보
    GameResult gameResult;
}
