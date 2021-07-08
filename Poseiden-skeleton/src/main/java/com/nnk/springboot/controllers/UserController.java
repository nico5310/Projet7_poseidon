package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.PasswordValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRepository userRepository;

    /**
     * Show userList HomePage
     * @return the list of user
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        log.info("Home page of User/list");
        model.addAttribute("users", userRepository.findAll());
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

        if (!result.hasErrors()) {
            PasswordValidator passwordValidator = new PasswordValidator();
            if (!passwordValidator.isValid(user.getPassword())) {
                log.error("ERROR, password isn't valid");
                return"/user/add";
            }else {
                log.info("SUCCESS, add new User");
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                model.addAttribute("users", userRepository.findAll());
                return "/user/list";
            }
        }
        log.error("ERROR, Add new user isn't possible");
        return "user/add";
    }

    /**
     * Show Update user form page
     * @return url user Update page
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Get Update form user with id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Update user by id
     * @return url userList page
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Delete user by id
     * @return url userList page
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
