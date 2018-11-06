package com.gomoku;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private GameService gameService;

	@Test
	public void testCreate() throws Exception{
		
		Gomoku gomoku = new Gomoku();
		gomoku.setId((long) 1);
		
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
