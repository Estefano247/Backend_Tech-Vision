package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Card;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {     
    Optional<Card> findByPan(Long pan);
    boolean existsByPan(Long pan);
    void deleteByPan(Long pan);
}
