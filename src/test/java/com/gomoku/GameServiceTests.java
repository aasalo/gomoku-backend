package com.gomoku;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gomoku.db.GameRepository;
import com.gomoku.game.Gomoku;
import com.gomoku.service.GameService;

@RunWith(SpringRunner.class)
public class GameServiceTests {

	@TestConfiguration
    static class GameServiceTestsContextConfiguration {
  
        @Bean
        public GameService gameService() {
            return new GameService();
        }
    }
	
	@Autowired
	GameService gameService;
	
	@MockBean
	private GameRepository gameRepository;

	@Test
	public void testCreate() {
		
		Gomoku gomoku = new Gomoku();
		Long id;
		
		when(gameRepository.save(gomoku)).thenReturn(gomoku);

		id = gameService.create();
		
		assertThat(id, is(equalTo(gomoku.getId())));
		
	}
	
	@Test
	public void testGetOne() {
	
		Gomoku gomoku = new Gomoku();
		Gomoku found;
		
		when(gameRepository.getOne(gomoku.getId())).thenReturn(gomoku);
		
		found = gameService.getGame(gomoku.getId());
		
		assertThat(found.getId(), is(equalTo(gomoku.getId())));
		
	}
}
