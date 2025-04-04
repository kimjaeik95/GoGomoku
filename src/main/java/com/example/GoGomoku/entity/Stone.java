package com.example.GoGomoku.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * packageName    : com.example.GoGomoku.entity
 * fileName       : Stone
 * author         : JAEIK
 * date           : 2/1/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/25       JAEIK       최초 생성
 */
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer x;

    private Integer y;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    private Integer turn;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "created_at")
    private Instant createdAt;

    public String getBlackPlayer() {
        return sessionId;
    }


}

