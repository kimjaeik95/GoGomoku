package com.example.GoGomoku.service;

import com.example.GoGomoku.dto.GameResponse;
import com.example.GoGomoku.dto.StoneRequest;
import com.example.GoGomoku.entity.Color;
import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.GameStatus;
import com.example.GoGomoku.entity.Stone;
import com.example.GoGomoku.repository.StoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * packageName    : com.example.GoGomoku.service
 * fileName       : BoardService
 * author         : JAEIK
 * date           : 1/31/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/25       JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class BoardService {
    private final StoneRepository stoneRepository;

    private Color[][] board;

    // 빈 오목판 생성
    public Color[][] createBoard() {
        int size = 15;
        board = new Color[size][size];
        return board;
    }

    // 오목돌 두기
    /*
    1. 오목돌 생성  (클라이언트가 x, y, color)
    2. 예외처리 유호성 , 동시성
     오목알생성 :  돌이 널인지, 같은색상인지 체크메서드 , 블랙돌 3+3 규칙예외 메서드
     오목알5개인지 체크 메서드,  count 메서드 ,
     승리시 Game 세션아이디 , 게임상태를 Win 으로 저장해주는로직이  메서드 분리해야할지
     체크해야할 부분 private 메서드를 분리해서 만들고,
     오목알 생성 로직에서 메서드 호출해야할듯? (돌을 넣고 5개가 되자마자 게임종료가되니)
     */
    public void createStone(StoneRequest stoneRequest) {
        stoneValidPosition(stoneRequest.x(),stoneRequest.y());

        Stone stone = stoneRequest.toStoneEntity();

        if (stone.getTurn() % 2 == 0) {  // 5 가 나머지가 0이면 짝수 , 1 이면 짝수
            if (!stoneRequest.color().equals("WHITE")) {
                throw new IllegalArgumentException("홀수턴에 화이트 돌이 올 수 없다.");
            }
        } else {
            if (!stoneRequest.color().equals("BLACK")) {
                throw new IllegalArgumentException("짝수턴에 블랙 돌이 올 수 없다.");
            }
        }
        stoneRepository.save(stone);
    }


    // 돌의 위치 유효성 검사
    private void stoneValidPosition(int x, int y) {
        if (board[x][y] != Color.EMPTY) {
            throw new IllegalArgumentException("이 위치에 돌이 있습니다.");
        }
    }
}
