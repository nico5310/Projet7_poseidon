package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    @Autowired
    RatingService ratingService;

    // SHOW RATING LIST HOMEPAGE
    @RequestMapping("/rating/list")
    public String home(Model model) {
        log.info("Show rating List");
        ratingService.home(model);
        return "/rating/list";
    }

    //ADD PAGE FORM
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        log.info("Add rating page formulaire");
        return "/rating/add";
    }
     //SAVE NEW RATING
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Add new rating isn't possible");
            return "/rating/add";
        }
        log.info("SUCCESS, Add new rating to ratingList");
        ratingService.validate(rating, model);
        return "redirect:/rating/list";
    }

    //UPDATE PAGE FORM
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Show Update form page by Id" + id);
        ratingService.showUpdateForm(id, model);
        return "/rating/update";
    }

    //UPDATE EXIST RATING
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Update rating isn't possible");
            return "redirect:/rating/update";
        }
        log.info("SUCCESS, Update rating by Id :"+ id);
        ratingService.updateRating(id, rating, model);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        log.info("SUCCESS, Delete rating with Id :" + id);
        ratingService.deleteRating(id, model);
        return "redirect:/rating/list";
    }
}
