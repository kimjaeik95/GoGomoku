package com.example.GoGomoku.repository;

import com.example.GoGomoku.entity.Stone;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface StoneRepository extends JpaRepository<Stone, Long> {
}
