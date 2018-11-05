package com.gomoku;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gomoku.db.GameRepository;
import com.gomoku.game.Gomoku;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTests {
	
	    @Autowired
	    private TestEntityManager entityManager;
	 
	    @Autowired
	    private GameRepository gameRepository;
	 
	    @Test
	    public void testCreate() {
	    	
	    	Gomoku gomoku = new Gomoku();
	    	
	    	Gomoku found = gameRepository.save(gomoku);
	    	
	    	assertThat(found.getId())
	        .isEqualTo(gomoku.getId());
	    	
	    }
	    
	    @Test
	    public void testGetOne() {
	    	
	    	Gomoku gomoku = new Gomoku();
	    	
	    	entityManager.persist(gomoku);
	    	entityManager.flush();
	    	
	    	Gomoku found = gameRepository.getOne(gomoku.getId());
	    	
	    	assertThat(found.getId())
	        .isEqualTo(gomoku.getId());
	    	
	    }
	    
}
