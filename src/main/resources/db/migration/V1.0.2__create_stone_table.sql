CREATE TABLE stone (
id          BIGINT        AUTO_INCREMENT   PRIMARY KEY COMMENT '돌 일렬번호',
game_id     BIGINT          COMMENT '돌이 속한 게임',
x           INT             COMMENT '돌 X 좌표',
y           INT             COMMENT '돌 Y 좌표',
color       VARCHAR(255)    COMMENT '돌의 색상 (BLACK, WHITE)',
turn        INT             COMMENT '돌의 놓은 순서',
session_id  VARCHAR(255)    COMMENT '플레이어 세션 아이디',
created_at  TIMESTAMP       COMMENT '돌 놓인 시간',
FOREIGN KEY (game_id) REFERENCES game(id),
CONSTRAINT uq_x_y UNIQUE(x, y)

);


