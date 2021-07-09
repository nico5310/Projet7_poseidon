package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.security.PasswordValidator;
import com.nnk.springboot.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User Controller is CRUD methods for User
 */
@Log4j2
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Show userList HomePage
     * @return the list of user
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        log.info("Home page of User/list");
        userService.home(model);
        return "/user/list";
    }

    /**
     * Add new user form page
     * @return url Add new user page
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "/user/add";
    }

    /**
     * Validate Add new user to userList
     * @return url user List page
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("ERROR, Add new user isn't possible");
            return "/user/add";
        }
        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(user.getPassword())) {
            log.error("ERROR, password isn't valid");
            return"/user/add";
        }else {
            log.info("SUCCESS, add new User");
            userService.validate(user, model);
            return "redirect:/user/list";
        }

    }

    /**
     * Show Update user form page
     * @return url user Update page
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("SUCCESS, Get Update form user with id");
       userService.showUpdateForm(id, model);
        return "user/update";
    }

    /**
     * Update user by id
     * @return url userList page
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Update user isn't valid");
            return "user/update";
        }
        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(user.getPassword())) {
            log.error("ERROR, password isn't valid");
            return"/user/update";
        }else {
            log.info("SUCCESS, add new User");
            userService.validate(user, model);
            return "redirect:/user/list";
        }

    }

    /**
     * Delete user by id
     * @return url userList page
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        log.info("SUCCESS, User is correctly delete");
        userService.deleteUser(id, model);
        return "redirect:/user/list";
    }
}
