package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Home User test")
    public void home() {
        //GIVEN
        List<User> userList = new ArrayList<>();
        //WHEN
        when(userRepository.findAll()).thenReturn(userList);
        //THEN
        assertEquals("user/list", userService.home(new ConcurrentModel()));
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Validate User test")
    public void validateUserTest() throws Exception {

        //GIVEN
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("Poseidon1@");
        user.setFullname("admin");
        user.setRole("ADMIN");
        assertThrows(Exception.class, () -> this.userService.validate(user));
    }

    @Test
    @DisplayName("ShowUpdate User test")
    public void showUpdateFormUserTest() {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("Poseidon1@");
        user.setFullname("admin");
        user.setRole("ADMIN");
        //WHEN
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        userService.showUpdateForm(1, new ConcurrentModel());
        //THEN
        verify(userRepository).findById((1));

    }

    @Test
    @DisplayName("update user test")
    public void updateUserTest() {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("Poseidon1@");
        user.setFullname("admin");
        user.setRole("ADMIN");
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        when(userRepository.save(user)).thenReturn(user);
        //GIVEN
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("admin");
        user1.setPassword("Poseidon1@");
        user1.setFullname("admin");
        user1.setRole("USER");
        userService.updateUser(1, user1, new ConcurrentModel());
        //WHEN

        //THEN
        verify(userRepository).findAll();
        verify(userRepository).save(user1);
        assertEquals(1, user1.getId().intValue());
        assertEquals("USER", user1.getRole());
    }

    @Test
    @DisplayName("Delete User test")
    public void deleteUserTest() {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("Poseidon1@");
        user.setFullname("admin");
        user.setRole("ADMIN");
        //WHEN
        userRepository.deleteById(1);
        //THEN
        verify(userRepository).deleteById(1);

    }

}

