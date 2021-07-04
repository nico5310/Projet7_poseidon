package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    @Autowired
    BidListService bidListService;

    // SHOW BID LIST HOMEPAGE
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        log.info("Show BidList page");
        bidListService.home(model);
        return "/bidList/list";
    }

    // ADD PAGE FORM
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("Add bid page formulaire");
        return "/bidList/add";
    }

    // SAVE NEW BID
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("ERROR, Add new bid isn't possible");
            return "bidList/add";
        }
        log.info("SUCCESS, Add new bid to BidList");
        bidListService.validate(bid, model);
        return "redirect:/bidList/list";
    }

    // UPDATE PAGE FORM
    @GetMapping("/bidList/update/{bidListId}")
    public String showUpdateForm(@PathVariable("bidListId") Integer bidListId, Model model) {
        log.info("Show Update form page by Id " + bidListId);
        bidListService.showUpdateForm(bidListId, model);
        return "bidList/update";
    }


    //UPDATE EXIST BID
    @PostMapping("/bidList/update/{bidListId}")
    public String updateBid(@PathVariable("bidListId") Integer bidListId, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Update bid isn't possible");
            return "redirect:/bidList/update";
        }
        log.info("SUCCESS, Update Bid by Id :"+ bidListId);
        bidListService.updateBid(bidListId, bidList, model);
        return "redirect:/bidList/list";
    }


    @GetMapping("/bidList/delete/{bidListId}")
    public String deleteBid(@PathVariable("bidListId") Integer bidListId, Model model) {
        log.info("SUCCESS, Delete bid with Id :" + bidListId);
        bidListService.deleteBid(bidListId, model);
        return "redirect:/bidList/list";
    }
}
