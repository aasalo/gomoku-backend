package com.gomoku.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gomoku.game.Gomoku;

@Repository
public interface GameRepository extends JpaRepository<Gomoku, Long>{
	
	public Gomoku save(Gomoku gomoku);
	public Gomoku getOne(Long id);
}
