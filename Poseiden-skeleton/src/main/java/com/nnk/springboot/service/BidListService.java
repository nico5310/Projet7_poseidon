package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Log4j2
@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;


    public String home (Model model) {
                log.info("Show bid list");
        model.addAttribute("bidList", bidListRepository.findAll());
        return "bidList/list";
    }

    public void validate (@Valid BidList bid, Model model) {
        log.info("Add new bid to bid List");
        bidListRepository.save(bid);
        model.addAttribute("bidList", bidListRepository.findAll());
    }

    public void showUpdateForm(Integer bidListId, Model model) {
        log.info("Find Bid by id to BidList");
        BidList bidList = bidListRepository.findById(bidListId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ bidListId));
        model.addAttribute("bidList", bidList);
    }

    public void updateBid(Integer bidListId, BidList bidList, Model model) {
        log.info("Update exist Bid by id" + bidListId);
        bidList.setBidListId(bidListId);
        bidListRepository.save(bidList);
        model.addAttribute("bidList",bidListRepository.findAll());
    }

    public void deleteBid(Integer bidListId, Model model) {
        log.info("Delete Bid by Id" + bidListId);
        BidList bidList = bidListRepository.findById(bidListId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + bidListId));
        bidListRepository.delete(bidList);
        model.addAttribute("bidList", bidListRepository.findAll());
    }



}
