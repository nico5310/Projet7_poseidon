package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Login Controller is CRUD methods for login
 */
@Log4j2
@Controller
@RequestMapping("/app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    /**
     * User login
     * @return url login page
     */
    @GetMapping("/login")
    public ModelAndView login() {
        log.info("Get login");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }


//    @GetMapping("/secure/article-details")
//    public ModelAndView getAllUserArticles() {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("users", userRepository.findAll());
//        mav.setViewName("user/list");
//        return mav;
//    }

    /**
     * Authentication isn't successful
     * @return error URL
     */
    @GetMapping("/error")
    public ModelAndView error() {
        log.error("ERROR, authentication isn't validate");
        ModelAndView mav = new ModelAndView();
        String errorMessage= "ERROR, authentication isn't validate.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

    @GetMapping("/errorUser")
    public ModelAndView errorUser() {
        log.error("ERROR, user already Exist");
        ModelAndView mav = new ModelAndView();
        String errorMessage= "ERROR, user already Exist.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("error");
        return mav;
    }

    /**
     * User log out connexion
     * @return url logout page
     */
    @GetMapping("/logout")
    public ModelAndView logout() {
        log.info("SUCCESS, user is log out");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logout");
        return mav;
    }

}
