package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

public class BidLIstIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	BidListRepository bidListRepository;


	@Test
	@DisplayName("BidList test")
	public void homeBidListTest() throws Exception {

		mockMvc.perform(get("/bidList/list"))
			   	.andExpect(status().isOk())
			   	.andExpect(view().name("/bidList/list"));

	}

	@Test
	@DisplayName("BidTests2")
	public void homeBidListTest2() throws Exception {

				BidList bidList = new BidList();
				bidList.setAccount("caisse");
				bidList.setType("action");
				bidList.setBidQuantity(Double.valueOf("10"));
				bidListRepository.save(bidList);

		mockMvc.perform(get("/bidList/list"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(view().name("/bidList/list"))
			   .andExpect(model().attribute("bidList", Matchers.hasSize(1)))
			   .andReturn();

	}

	@Test
	@DisplayName("AddBidForm")
	public void addBidFormTest() throws Exception {

		mockMvc.perform(get("/bidList/add"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("/bidList/add"));

	}

	@Test
	@DisplayName("ValidateBidList")
	public void validateBidListTest() throws Exception {
		BidList bidList = new BidList();
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(post("/bidList/validate"))
				.andExpect(status().isOk());

	}

	@Test
	@DisplayName("ShowUpdateForm")
	public void showUpdateFormTest() throws Exception {
		BidList bidList = new BidList();
		bidList.setAccount("caisse");
		bidList.setType("action");
		bidList.setBidQuantity(10.00);
		bidListRepository.save(bidList);

		mockMvc.perform(get("/bidList/update/1"))
			   .andExpect(status().isOk());
	}

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
													   .param("bidQuantity", "20.00"))
													   .andExpect(redirectedUrl("/bidList/list"));
		mockMvc.perform(get("/bidList/update/1"))
			   .andExpect(status().isOk())
			   .andExpect(model().attribute("bidList", Matchers.hasProperty("bidQuantity", is(20.00))));
	}

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
