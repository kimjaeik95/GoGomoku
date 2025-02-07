package com.example.GoGomoku.repository;

import com.example.GoGomoku.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.example.GoGomoku.repository
 * fileName       : GameRepository
 * author         : JAEIK
 * date           : 2/7/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/7/25       JAEIK       최초 생성
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
