package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/create_db_script-Test.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BidListIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	BidListRepository bidListRepository;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@WithMockUser
	@Test
	@DisplayName("BidList test")
	public void homeBidListAdminTest() throws Exception {

		mockMvc.perform(get("/bidList/list"))
			   	.andExpect(status().isOk())
			   	.andExpect(view().name("/bidList/list"));

	}

	@Test
	@DisplayName("BidList test")
	public void homeBidListAnonymousTest() throws Exception {

		mockMvc.perform(get("/bidList/list"))
			   .andExpect(status().isUnauthorized());

	}


	@WithMockUser
	@Test
	@DisplayName("BidTests2")
	public void homeBidListTest2() throws Exception {

		BidList bidList = new BidList();
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(get("/bidList/list"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("/bidList/list"))
			   .andExpect(model().attribute("bidList", Matchers.hasSize(1)))
			   .andReturn();

	}

	@WithMockUser
	@Test
	@DisplayName("AddBidForm")
	public void addBidFormTest() throws Exception {

		mockMvc.perform(get("/bidList/add"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("/bidList/add"));

	}

	@WithMockUser
	@Test
	@DisplayName("ValidateBidList")
	public void validateBidListTest() throws Exception {

		BidList bidList = new BidList();
		bidList.setBidListId(1);
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(get("/bidList/list"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("bidList", Matchers.hasSize(1)));

	}

	@WithMockUser
	@Test
	@DisplayName("ShowUpdateForm")
	public void showUpdateFormTest() throws Exception {

		BidList bidList = new BidList();
		bidList.setBidListId(1);
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(get("/bidList/update/1"))
			   .andExpect(status().isOk());

	}

	@WithMockUser
	@Test
	@DisplayName("UpdateBid")
	public void updateBidTest() throws Exception {

		BidList bidList = new BidList();
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
				.param("account", "caisse")
				.param("type", "action")
				.param("bidQuantity", "20.0"))
			   .andExpect(redirectedUrl("/bidList/list"));

		mockMvc.perform(get("/bidList/update/1"))
			   .andExpect(status().isOk())
			   .andExpect(model().attribute("bidList", Matchers.hasProperty("bidQuantity", is(20.00))));

	}

	@WithMockUser
	@Test
	@DisplayName("deleteBid")
	public void deleteBidTest() throws Exception {

		BidList bidList = new BidList();
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(get("/bidList/delete/1"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(redirectedUrl("/bidList/list"));

		mockMvc.perform(get("/bidList/list"))
			   .andExpect(status().isOk())
			   .andExpect(model().attribute("bidList", Matchers.hasSize(0)));

	}

}
