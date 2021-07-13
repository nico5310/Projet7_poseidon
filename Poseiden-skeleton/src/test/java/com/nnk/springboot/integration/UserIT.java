package com.nnk.springboot.integration;
;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/create_db_script-Test.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserIT  {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser
    @Test
    @DisplayName("User home test")
    public void homeUserAdminTest() throws Exception {

        mockMvc.perform(get("/user/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/user/list"));

    }

    @Test
    @DisplayName("User home test")
    public void homeUserAnonyousTest() throws Exception {

        mockMvc.perform(get("/user/list"))
               .andExpect(status().isUnauthorized());

    }

    @WithMockUser
    @Test
    @DisplayName("UserTests2")
    public void homeUserTest2() throws Exception {

        User user = new User();
        user.setUsername("userName");
        user.setPassword("Poseidon1@");
        user.setFullname("fullName");
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(get("/user/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("users", Matchers.hasSize(1)))
               .andReturn();

    }

    @WithMockUser
    @Test
    @DisplayName("AddUserForm")
    public void addUserFormTest() throws Exception {

        mockMvc.perform(get("/user/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/user/add"));

    }

    @WithMockUser
    @Test
    @DisplayName("ValidateUserList")
    public void validateUserListTest() throws Exception {

        User user = new User();
        user.setUsername("userName");
        user.setPassword("Poseidon1@");
        user.setFullname("fullName");
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(get("/user/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("users", Matchers.hasSize(1)));

    }

    @WithMockUser
    @Test
    @DisplayName("ShowUpdateForm")
    public void showUpdateFormTest() throws Exception {

        User user = new User();
        user.setUsername("userName");
        user.setPassword("Poseidon1@");
        user.setFullname("fullName");
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(get("/user/update/1"))
               .andExpect(status().isOk());
    }

    @WithMockUser (username = "admin", password = "Poseidon1@")
    @Test
    @DisplayName("UpdateUser")
    public void updateUserTest() throws Exception {

        User user = new User();
        user.setUsername("userName");
        user.setPassword("Poseidon1@");
        user.setFullname("fullname");
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
               .param("username", "username")
                .param("password", "Poseidon1@")
               .param("fullname", "fullname")
               .param("role", "ADMIN"))
               .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/user/update/1"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("user", Matchers.hasProperty("role", is("ADMIN"))));
    }

    @WithMockUser
    @Test
    @DisplayName("deleteUser")
    public void deleteUserTest() throws Exception {

        User user = new User();
        user.setUsername("userName");
        user.setPassword("Poseidon1@");
        user.setFullname("fullName");
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(get("/user/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/user/list"));

        mockMvc.perform(get("/user/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("users", Matchers.hasSize(0)));
    }



}
