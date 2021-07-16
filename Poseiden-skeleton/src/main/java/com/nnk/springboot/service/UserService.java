package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * User Service is CRUD methods to User
 */
@Log4j2
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

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
    public void validate (User user) throws Exception {
        if(user.getUsername() == "") {
            log.info("Add new user to user List");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else {
            throw  new Exception("User already exists");
        }

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
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
