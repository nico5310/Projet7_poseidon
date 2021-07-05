package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserIT  {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("User home test")
    public void homeUserTest() throws Exception {

        mockMvc.perform(get("/user/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/user/list"));

    }

    @Test
    @DisplayName("UserTests2")
    public void homeUserTest2() throws Exception {

//        User user = new User();
//        user.setUsername("userName");
//        user.setPassword("123");
//        user.setFullname("fullName");
//        user.setRole("user");
//        userRepository.save(user);
//
//        mockMvc.perform(get("/user/list"))
//               .andDo(print())
//               .andExpect(status().isOk())
//               .andExpect(view().name("/user/list"))
//               .andExpect(model().attribute("users", Matchers.hasSize(1)))
//               .andReturn();

    }

    @Test
    @DisplayName("AddUserForm")
    public void addUserFormTest() throws Exception {

        mockMvc.perform(get("/user/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/user/add"));

    }

    @Test
    @DisplayName("ValidateUserList")
    public void validateUserListTest() throws Exception {
//        User user = new User();
//        user.setUsername("userName");
//        user.setFullname("fullName");
//        user.setRole("user");
//        userRepository.save(user);
//
//        mockMvc.perform(post("/user/validate"))
//               .andExpect(status().isOk());

    }

    @Test
    @DisplayName("ShowUpdateForm")
    public void showUpdateFormTest() throws Exception {
//        User user = new User();
//        user.setUsername("userName");
//        user.setFullname("fullName");
//        user.setRole("user");
//        userRepository.save(user);
//
//        mockMvc.perform(get("/user/update/1"))
//               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("UpdateUser")
    public void updateUserTest() throws Exception {
//        User user = new User();
//        user.setUsername("userName");
//        user.setFullname("fullName");
//        user.setRole("user");
//        userRepository.save(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
//                                              .param("userName", "userName")
//                                              .param("fullName", "fullName")
//                                              .param("role", "admin"))
//               .andExpect(redirectedUrl("/user/list"));
//        mockMvc.perform(get("/user/update/1"))
//               .andExpect(status().isOk())
//               .andExpect(model().attribute("user", Matchers.hasProperty("role", is("admin"))));
    }

    @Test
    @DisplayName("deleteUser")
    public void deleteUserTest() throws Exception {
//        User user = new User();
//        user.setUsername("userName");
//        user.setFullname("fullName");
//        user.setRole("user");
//        userRepository.save(user);
//
//
//        mockMvc.perform(get("/user/delete/1"))
//               .andExpect(status().is3xxRedirection())
//               .andExpect(redirectedUrl("/user/list"));
//
//        mockMvc.perform(get("/user/list"))
//               .andExpect(status().isOk())
//               .andExpect(model().attribute("user", Matchers.hasSize(0)));
    }



}
