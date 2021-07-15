package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;

/**
 * Trade Service is CRUD methods of Trade
 */
@Log4j2
@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    /**
     * Show Trade List
     * @return all trade
     */
    public String home (Model model) {
        log.info("Show trade list");
        model.addAttribute("trade", tradeRepository.findAll());
        return "trade/list";
    }

    /**
     * Add new trade
     */
    public void validate (@Valid Trade trade) {
        log.info("Add new trade to bid List");
        tradeRepository.save(trade);
    }

    /**
     * Show update form
     */
    public void showUpdateForm(Integer tradeId, Model model) {
        log.info("Find trade by id to BidList");
        Trade trade  = tradeRepository.findById(tradeId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ tradeId));
        model.addAttribute("trade", trade);
    }

    /**
     * Update trade by id
     */
    public void updateTrade(Integer tradeId, Trade trade, Model model) {
        log.info("Update exist Bid by id" + tradeId);
        trade.setTradeId(tradeId);
        tradeRepository.save(trade);
        model.addAttribute("trade",tradeRepository.findAll());
    }

    /**
     * Delete trade by id
     */
    public void deleteTrade(Integer tradeId, Model model) {
        log.info("Delete Trade by Id" + tradeId);
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + tradeId));
        tradeRepository.delete(trade);
        model.addAttribute("trade", tradeRepository.findAll());
    }


}
