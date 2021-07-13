package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * BidList Controller is CRUD methods for bidList.
 */

@Log4j2
@Controller
public class BidListController {

    @Autowired
    private BidListService bidListService;

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Show bidList HomePage
     * @return the list of bids
     */

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        log.info("Show BidList homepage");
        bidListService.home(model);
        return "/bidList/list";
    }

    /**
     * Add new bid form page
     * @return url Add new bid page
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("Add bid page formulaire");
        return "/bidList/add";
    }

    /**
     * Validate Add new bid to bidList
     * @return url bidList page
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("ERROR, Add new bid isn't possible");
            return "bidList/add";
        }
        log.info("SUCCESS, Add new bid to BidList");
        model.addAttribute("bidList",bidListRepository.findAll());
        bidListService.validate(bid);
        return "redirect:/bidList/list";
    }

    /**
     * Show Update bid form page
     * @return url bid Update page
     */
    @GetMapping("/bidList/update/{bidListId}")
    public String showUpdateForm(@PathVariable("bidListId") Integer bidListId, Model model) {
        log.info("Show Update form page by Id " + bidListId);
        bidListService.showUpdateForm(bidListId, model);
        return "bidList/update";
    }

    /**
     * Update bid by id
     * @return url bidList page
     */
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

    /**
     * Delete bid by id
     * @return url bidList page
     */
    @GetMapping("/bidList/delete/{bidListId}")
    public String deleteBid(@PathVariable("bidListId") Integer bidListId, Model model) {
        log.info("SUCCESS, Delete bid with Id :" + bidListId);
        bidListService.deleteBid(bidListId, model);
        return "redirect:/bidList/list";
    }
}
