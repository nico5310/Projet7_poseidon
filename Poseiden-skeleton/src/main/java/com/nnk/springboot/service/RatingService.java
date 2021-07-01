package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Log4j2
@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public String home (Model model) {
        log.info("Show rating list");
        model.addAttribute("rating", ratingRepository.findAll());
        return "rating/list";
    }

    public void validate (@Valid Rating rating, Model model) {
        log.info("Add new rating to bid List");
        ratingRepository.save(rating);
        model.addAttribute("rating", ratingRepository.findAll());
    }

    public void showUpdateForm(Integer id, Model model) {
        log.info("Find rating by id to ratingList");
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
        model.addAttribute("rating", rating);
    }

    public void updateRating(Integer id,Rating rating, Model model) {
        log.info("Update exist rating by id" + id);
        rating.setId(id);
        ratingRepository.save(rating);
        model.addAttribute("rating",ratingRepository.findAll());
    }

    public void deleteRating(Integer id, Model model) {
        log.info("Delete rating by Id" + id);
        Rating rating  = ratingRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("rating", ratingRepository.findAll());
    }


}
