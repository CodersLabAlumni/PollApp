package com.pollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollapp.entity.GameScore;

@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long>{

}
