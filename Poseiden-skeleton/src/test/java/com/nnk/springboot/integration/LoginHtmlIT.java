package com.nnk.springboot.integrations;

import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LoginHtmlIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("Home test")
    public void loginTest() throws Exception {

        mockMvc.perform(get("/app/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));

    }

    @Test
    @DisplayName("Error test")
    public void errorTest() throws Exception {

        mockMvc.perform(get("/app/error"))
               .andExpect(status().isOk())
               .andExpect(view().name("403"));

    }

    @Test
    @DisplayName("Error test")
    public void logoutTest() throws Exception {

        mockMvc.perform(get("/app/logout"))
               .andExpect(status().isOk())
               .andExpect(view().name("logout"));

    }

}
