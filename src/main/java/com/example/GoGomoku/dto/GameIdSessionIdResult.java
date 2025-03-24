package com.example.GoGomoku.dto;

/**
 * packageName    : com.example.GoGomoku.dto
 * fileName       : gameIdSessionIdResult
 * author         : JAEIK
 * date           : 3/22/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/22/25       JAEIK       최초 생성
 */
public record GameIdSessionIdResult(
        Long gameId,
        String sessionId
){
}
