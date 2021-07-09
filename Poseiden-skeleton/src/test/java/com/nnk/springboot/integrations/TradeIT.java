package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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

    public class TradeIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TradeRepository tradeRepository;

    @WithMockUser
    @Test
    @DisplayName("Trade home test")
    public void homeTradeTest() throws Exception {

        mockMvc.perform(get("/trade/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/trade/list"));

    }

    @WithMockUser
    @Test
    @DisplayName("TradeTests2")
    public void homeTradeTest2() throws Exception {

        Trade trade = new Trade();
        trade.setAccount("caisse");
        trade.setType("action");
        trade.setBuyQuantity(10.0);
        tradeRepository.save(trade);

        mockMvc.perform(get("/trade/list"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("/trade/list"))
               .andExpect(model().attribute("trade", Matchers.hasSize(1)))
               .andReturn();

    }

    @WithMockUser
    @Test
    @DisplayName("AddTradeForm")
    public void addTradeFormTest() throws Exception {

        mockMvc.perform(get("/trade/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/trade/add"));

    }

    @WithMockUser
    @Test
    @DisplayName("ValidateTradeList")
    public void validateTradeListTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("caisse");
        trade.setType("action");
        trade.setBuyQuantity(10.0);
        tradeRepository.save(trade);

        mockMvc.perform(post("/trade/validate"))
               .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    @DisplayName("ShowUpdateForm")
    public void showUpdateFormTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("caisse");
        trade.setType("action");
        trade.setBuyQuantity(10.0);
        tradeRepository.save(trade);

        mockMvc.perform(get("/trade/update/1"))
               .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    @DisplayName("UpdateTrade")
    public void updateTradeTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("caisse");
        trade.setType("action");
        trade.setBuyQuantity(10.0);
        tradeRepository.save(trade);
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
                                              .param("account", "caisse")
                                              .param("type", "action")
                                              .param("buyQuantity", "20.00"))
               .andExpect(redirectedUrl("/trade/list"));
        mockMvc.perform(get("/trade/update/1"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("trade", Matchers.hasProperty("buyQuantity", is(20.00))));
    }

    @WithMockUser
    @Test
    @DisplayName("deleteTrade")
    public void deleteBidTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("caisse");
        trade.setType("action");
        trade.setBuyQuantity(10.0);
        tradeRepository.save(trade);

        mockMvc.perform(get("/trade/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/trade/list"));

        mockMvc.perform(get("/trade/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("trade", Matchers.hasSize(0)));
    }



}


