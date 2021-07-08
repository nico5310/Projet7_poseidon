package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.validation.Valid;

/**
 * User Service is CRUD methods to User
 */
@Log4j2
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Show user List
     * @return all userList
     */
    public String home (Model model) {
        log.info("Show user list");
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * Add new user
     */
    public void validate (@Valid User user, Model model) {
        log.info("Add new user to user List");
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
    }

    /**
     * Show update form
     */
    public void showUpdateForm(Integer id, Model model) {
        log.info("Find user by id to userList");
        User user  = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
        user.setPassword("");
        model.addAttribute("user", user);
    }

    /**
     * Update user by id
     */
    public void updateUser(Integer id, User user, Model model) {
        log.info("Update exist user by id" + id);
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users",userRepository.findAll());
    }

    /**
     * Delete user by id
     */
    public void deleteUser(Integer id, Model model) {
        log.info("Delete User by Id" + id);
        User user = userRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
    }

}
