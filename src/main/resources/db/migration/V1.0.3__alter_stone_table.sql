ALTER TABLE stone ADD CONSTRAINT uq_gameid_x_y UNIQUE (game_id, x, y);
