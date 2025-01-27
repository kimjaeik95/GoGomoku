CREATE TABLE game (
id                  BIGINT        PRIMARY KEY        AUTO_INCREMENT  COMMENT '게임 일렬번호',
start_time          TIMESTAMP    COMMENT '게임 시작 시간',
end_time            TIMESTAMP    COMMENT '게임 종료 시간',
winner_session_id   VARCHAR(255) COMMENT '게임 승자 세션 아이디',
status              VARCHAR(255) COMMENT '게임 상태'

);

