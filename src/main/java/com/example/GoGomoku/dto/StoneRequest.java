package com.example.GoGomoku.dto;

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
}
