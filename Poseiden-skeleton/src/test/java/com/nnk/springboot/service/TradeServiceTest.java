package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {TradeService.class})
@ExtendWith(SpringExtension.class)
public class TradeServiceTest {

    @MockBean
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @Test
    @DisplayName("Home Trade test")
    public void homeTradeTest() {
        //GIVEN
        List<Trade> tradeList = new ArrayList<>();
        //WHEN
        when(tradeRepository.findAll()).thenReturn(tradeList);
        //THEN
        assertEquals("trade/list", tradeService.home(new ConcurrentModel()));
        verify(tradeRepository).findAll();
    }

    @Test
    @DisplayName("Validate Trade test")
    public void validateTradeTest() {
        //GIVEN
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        //WHEN
        when(tradeRepository.save(trade)).thenReturn(trade);
        tradeService.validate(trade);
        //THEN
        verify(tradeRepository).save(trade);
    }

    @Test
    @DisplayName("ShowUpdate Trade test")
    public void showUpdateFormTradeTest() {
        //GIVEN
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        //WHEN
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        tradeService.showUpdateForm(1, new ConcurrentModel());
        //THEN
        verify(tradeRepository).findById(1);
    }

    @Test
    @DisplayName("update Trade test")
    public void updateTradeTest() {
        //GIVEN
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        List<Trade> tradeList = new ArrayList<>();
        when(tradeRepository.findAll()).thenReturn(tradeList);
        when(tradeRepository.save(trade)).thenReturn(trade);
        Trade trade1 = new Trade();
        trade1.setTradeId(1);
        trade1.setAccount("Account");
        trade1.setType("Type");
        trade1.setBuyQuantity(20.0);
        tradeService.updateTrade(1, trade1, new ConcurrentModel());
        //WHEN

        //THEN
        verify(tradeRepository).findAll();
        verify(tradeRepository).save(trade1);
        assertEquals(1, trade1.getTradeId().intValue());
        assertEquals(20.0, trade1.getBuyQuantity().intValue());
    }

    @Test
    @DisplayName("Delete Trade test")
    public void deleteTradeTest() {
        //GIVEN
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
        //WHEN
        tradeRepository.deleteById(1);
        //THEN
        verify(tradeRepository).deleteById(1);
    }
}

