package com.example.GoGomoku.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * packageName    : com.example.GoGomoku.entity
 * fileName       : Game
 * author         : JAEIK
 * date           : 2/1/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/25       JAEIK       최초 생성
 */
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "winner_session_id")
    private String winnerSessionId;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToMany(mappedBy = "game")
    private List<Stone> stones;

    /* Game 생성은 클라이언트에게 받는 정보가 없기 때문에
       게임시작시간, 상태(진행중)으로 기본으로 저장되게했다.
       나머지 필드는 Null 이지만 추후 게임이 종료될때 업데이트로 반영될것이다.
     */
    @PrePersist
    public void prePersist() {
        this.startTime = Instant.now();
        this.status = GameStatus.IN_PROGRESS;
    }

    public void updateWinGame(String winnerSessionId) {
        this.endTime = Instant.now();
        this.winnerSessionId = winnerSessionId;
        this.status = GameStatus.WIN;
    }

    public void updateDrawGame() {
        this.endTime = Instant.now();
        this.status = GameStatus.DRAW;
    }

    public void updateErrorGame() {
        this.endTime = Instant.now();
        this.status = GameStatus.ERROR_GAME_END;
    }
}
