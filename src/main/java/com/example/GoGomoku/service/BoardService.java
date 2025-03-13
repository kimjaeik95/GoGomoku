package com.example.GoGomoku.service;


import com.example.GoGomoku.dto.StoneRequest;
import com.example.GoGomoku.entity.Game;
import com.example.GoGomoku.entity.Stone;
import com.example.GoGomoku.repository.GameRepository;
import com.example.GoGomoku.repository.StoneRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



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
    private final GameRepository gameRepository;

    private Stone[][] board;

    public void createGame() {
        Game game = new Game();
        gameRepository.save(game);
    }

    // 빈 오목판 생성
    public Stone[][] createBoard() {
        int size = 15;
        board = new Stone[size][size];
        return board;
    }

    // 오목돌 두기
    /*
    1. 오목돌 생성  (x,y 좌표 값 확인해서 빈곳인지 확인해야함) , 같은색상인지 체크
    2. 오목알5개인지 체크 메서드 (가로 5개 연속.세로 5개 연속,대각선 ↘(우하향) 5개 연속,대각선 ↙(좌하향) 5개 연속)
    3.블랙돌 3+3 규칙예외 메서드
    4.승리시 Game 세션아이디 , 게임상태를 Win 으로 저장해주는로직
     */
    public void createStone(StoneRequest stoneRequest, Long gameId, HttpSession session) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));

        // 로그인 기능이 없으므로 sessionId로 흑/백 사용자 저장
        String sessionId = session.getId();
        // 게임에서 마지막 돌을 찾는다.
        int latestTurn = stoneRepository.findLatestTurnGameId(gameId);
        // 돌을생성할때마다 turn 증가
        int newTurn = latestTurn + 1;
        // x,y 공간확인
        stoneValidPosition(stoneRequest.x(), stoneRequest.y());
        // 엔 -Dto 변환
        Stone stone = stoneRequest.toStoneEntity(game, newTurn, sessionId);

        // 홀짝 턴 검사
        if (newTurn % 2 == 0) {
            if (!stoneRequest.color().equals("WHITE")) {
                throw new IllegalArgumentException("짝수턴에 화이트 돌만 와야 합니다.");
            }
        } else {
            if (!stoneRequest.color().equals("BLACK")) {
                throw new IllegalArgumentException("홀수턴에는 블랙 돌만 와야 합니다.");
            }
        }

        // 돌 저장
        stoneRepository.save(stone);

        // 결과 판정 (승리 or 드로우)
        if (checkWinStone(stone)) {
            game.updateWinGame(sessionId);
        } else if (checkDrawStone(stone)) {
            game.updateDrawGame();
        } else {
            game.updateErrorGame();
        }

        gameRepository.save(game);
    }


    // 돌의 위치 유효성 검사
    private void stoneValidPosition(int x, int y) {
        if (board[x][y] != null) {
            throw new IllegalArgumentException("이 위치에 돌이 있습니다.");
        }
    }

    // 블랙 3+3

    // 무승부 조건 확인 메소드 (오목판이 가득차면 드로우)
    private boolean checkDrawStone(Stone stone) {
        for (int i = 0; i <board.length; i++) { // 행탐색
            for (int j = 0; j <board[i].length; j++) { // i행의 전체열 탐색
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    // 승리 조건 확인 메소드
    private boolean checkWinStone(Stone stone) {
        return checkStoneFiveCount(stone, 1, 0) || // x축 아래방향 승리
                checkStoneFiveCount(stone, -1, 0) || // x축 위방향 승리
                checkStoneFiveCount(stone, 0, 1) || // 오른쪽열(y)승리
                checkStoneFiveCount(stone, 0, -1) || // 왼쪽열(y)승리
                checkStoneFiveCount(stone, 1, 1) || // ↘ (오른쪽 아래 대각선)
                checkStoneFiveCount(stone, -1, -1) || // ↖ (왼쪽 위 대각선)
                checkStoneFiveCount(stone, 1, -1) || // ↙ (왼쪽 아래 대각선)
                checkStoneFiveCount(stone, -1, 1); // ↗ (오른쪽 위 대각선)
    }
    /* 연속된 돌이 5개인지 확인하는 메소드
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  2차원배열 탐색
    dx = 1, dy = 0  아래( x축 세로방향)으로 이동하려면 newX 값을 +1씩 증가시킨다,
    왼쪽(음의 y축 왼쪽방향)으로 이동하려면 newY 값을 -1씩 감소
    dx와 dy를 이용해 현재 위치(x, y)에서 이동할 위치 (newX, newY)를 계산
    탐색후 count = 5
    */
    private boolean checkStoneFiveCount(Stone stone, int dx, int dy) {
        int count = 1;
        int x = stone.getX();
        int y = stone.getY();

        for (int i = 1; i <= 4; i++) {
            int nextX = x + i * dx;  // 1 + 1 * 1
            int nextY = y + i * dy;  // 1 + 1 * 1

            if (nextX < 0 || nextX >= 15 || nextY < 0 || nextY >= 15
                    || board[nextX][nextY] == null
                    || !board[nextX][nextY].getColor().equals(stone.getColor())) {
                break;
            } else {
                count++;
            }
        }
        for (int i = 1; i <= 4; i++) {
            int nextX = x - i * dx;
            int nextY = y - i * dy;

            if (nextX < 0 || nextX >= 15 || nextY < 0 || nextY >= 15
                    || board[nextX][nextY] == null
                    || !board[nextX][nextY].getColor().equals(stone.getColor())) {
                break;
            } else {
                count++;
            }
        }
        return count >= 5;
    }
}

