package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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

/**
 * Rating Controller is CRUD methods for Rating
 */
@Log4j2
@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    /**
     * Show rating HomePage
     * @return the list of rating
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        log.info("Show rating List");
        ratingService.home(model);
        return "/rating/list";
    }

    /**
     * Add new rating form page
     * @return url Add new rating page
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        log.info("Add rating page formulaire");
        return "/rating/add";
    }

    /**
     * Validate Add new rating to ratingList
     * @return url ratingList page
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("ERROR, Add new rating isn't possible");
            return "/rating/add";
        }
        log.info("SUCCESS, Add new rating to ratingList");
        model.addAttribute("rating", ratingRepository.findAll());
        ratingService.validate(rating);
        return "redirect:/rating/list";
    }

    /**
     * Show Update rating form page
     * @return url rating Update page
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Show Update form page by Id" + id);
        ratingService.showUpdateForm(id, model);
        return "/rating/update";
    }

    /**
     * Update rating by id
     * @return url ratingList page
     */
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

    /**
     * Delete rating by id
     * @return url ratingList page
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        log.info("SUCCESS, Delete rating with Id :" + id);
        ratingService.deleteRating(id, model);
        return "redirect:/rating/list";
    }
}
