package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Log4j2
@Controller
public class TradeController {

    @Autowired
    TradeService tradeService;

    // SHOW TRADE LIST HOMEPAGE
    @RequestMapping("/trade/list")
    public String home(Model model) {
        log.info("Show Trade page");
        tradeService.home(model);
        return "trade/list";
    }
    // ADD PAGE FORM
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        log.info("Add trade page formulaire");
        return "trade/add";
    }
    // SAVE NEW TRADE
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Add new trade isn't possible");
            return "bidList/add";
        }
        log.info("SUCCESS, Add new trade to BidList");
        tradeService.validate(trade, model);
        return "redirect:/trade/list";
    }
    // UPDATE PAGE FORM
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer tradeId, Model model) {
        log.info("Show Update form page by Id " + tradeId);
        tradeService.showUpdateForm(tradeId, model);
        return "trade/update";
    }
    //UPDATE EXIST BID
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer tradeId, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Update trade isn't possible");
            return "redirect:/trade/update";
        }
        log.info("SUCCESS, Update trade by Id :"+ tradeId);
        tradeService.updateTrade(tradeId, trade, model);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer tradeId, Model model) {
        log.info("SUCCESS, Delete trade with Id :" + tradeId);
        tradeService.deleteTrade(tradeId, model);
        return "redirect:/trade/list";
    }
}
