package com.gomoku;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gomoku.controller.GameController;
import com.gomoku.game.Gomoku;
import com.gomoku.service.GameService;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTests {

	private static final Logger logger = LoggerFactory.getLogger(GameControllerTests.class);
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private GameService gameService;
	
	@Before
	public void before( ) {
		Gomoku gomoku = new Gomoku();
		Long id = gomoku.getId();
	}

	@Test
	public void testCreate() throws Exception{
		
		Gomoku gomoku = new Gomoku();
		gomoku.setId((long) 1);
		
		logger.info("kräähkyli " + gomoku.getId().toString() + " " + gomoku.getStatus());
		
		when(gameService.create()).thenReturn(gomoku.getId());
		
		mvc.perform(post("/game")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(gomoku.getId().toString())));
		
	}
	
	@Test
	public void testGetGame() throws Exception{
		
		Gomoku gomoku = new Gomoku();
		gomoku.setId((long) 1);
		
		when(gameService.getGame(gomoku.getId())).thenReturn(gomoku);
		
		mvc.perform(get("/game/" + gomoku.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))) // This is not safe
				.andExpect(jsonPath("$.whoseTurn", is("BLACK")))
				.andExpect(jsonPath("$.status", is("ONGOING")));
	}

}
