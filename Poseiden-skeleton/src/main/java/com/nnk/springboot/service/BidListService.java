package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * BidList Service is CRUD methods for BidList
 */
@Log4j2
@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    /**
     * Show bid List
     * @return all bidList
     */
    public String home (Model model) {
        log.info("Show bidList List");
        model.addAttribute("bidList", bidListRepository.findAll());
        return "bidList/list";
    }

    /**
     * Add new bid
     */
    public void validate(BidList bid) {
        log.info("Add new bid to bid List");
        bidListRepository.save(bid);


    }

    /**
     * Show update form
     */
    public void showUpdateForm(Integer bidListId, Model model) {
        log.info("Find Bid by id to BidList");
        BidList bidList = bidListRepository.findById(bidListId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ bidListId));
        model.addAttribute("bidList", bidList);
    }

    /**
     * Update bid by id
     */
    public void updateBid(Integer bidListId, BidList bidList, Model model) {
        log.info("Update exist Bid by id" + bidListId);
        bidList.setBidListId(bidListId);
        bidListRepository.save(bidList);
        model.addAttribute("bidList",bidListRepository.findAll());
    }

    /**
     * Delete bid by id
     */
    public void deleteBid(Integer bidListId, Model model) {
        log.info("Delete Bid by Id" + bidListId);
        BidList bidList = bidListRepository.findById(bidListId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + bidListId));
        bidListRepository.delete(bidList);
        model.addAttribute("bidList", bidListRepository.findAll());
    }



}
