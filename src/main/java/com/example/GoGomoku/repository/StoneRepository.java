package com.example.GoGomoku.repository;

import com.example.GoGomoku.entity.Stone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.example.GoGomoku.repository
 * fileName       : StoneRepository
 * author         : JAEIK
 * date           : 2/1/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/25       JAEIK       최초 생성
 */
@Repository
public interface StoneRepository extends JpaRepository<Stone, Long> {
    @Query("SELECT COALESCE(MAX(s.turn), 0) FROM Stone s WHERE s.game.id = :gameId")
    int findLatestTurnGameId(@Param("gameId") Long gameId);

    boolean existsByGameIdAndXAndY(Long gameId, int x, int y);
}
