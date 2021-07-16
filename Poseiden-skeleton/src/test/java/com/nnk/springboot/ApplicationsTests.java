package com.nnk.springboot;

import com.nnk.springboot.controllers.LoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ApplicationsTests {

    @Autowired
    private LoginController loginController;

    @Test
    void contextLoads() {
        assertThat(loginController).isNotNull();
    }

}
