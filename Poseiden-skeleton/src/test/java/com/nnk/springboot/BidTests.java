package com.nnk.springboot;

import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class BidTests {

	@Autowired
	BidListRepository bidListRepository;

	@Autowired
	private BidListService bidListService;

	@Autowired
	MockMvc mockMvc;




	@Test
	@DisplayName("BidTests")
	public void homeBidListTest() throws Exception {

		mockMvc.perform(post("/bidList/validate").param("account", "form")).andExpect(status().is2xxSuccessful()).andExpect(model().attributeExists());

	}
}
