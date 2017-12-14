/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.ronaldo.dxtsnacksapi.controller.SnackController;

public class SnacksControllerTests extends DxtsnacksApiApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private SnackController snackController;
	
	@Before
	public void setUp() {
		// injeta o controller para que funcione como se tivesse executando a aplicação normalmente.
		this.mockMvc = MockMvcBuilders.standaloneSetup(snackController).build();
	}
	
	@Test
	public void testGETSnacksController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/snack")).
		andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
