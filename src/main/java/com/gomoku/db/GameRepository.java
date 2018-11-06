package com.gomoku.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomoku.game.Gomoku;

@Repository
public interface GameRepository extends JpaRepository<Gomoku, Long>{
	
	public Gomoku save(Gomoku gomoku);
	public Gomoku getOne(Long id);
}
