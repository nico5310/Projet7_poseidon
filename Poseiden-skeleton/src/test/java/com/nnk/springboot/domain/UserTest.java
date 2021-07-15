package com.nnk.springboot.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserTest {

    @Test
    @DisplayName("Constructor User Test")
    public void constructorUserTest() {

        User actualUser = new User("admin", "admin", "ADMIN");

        assertNull(actualUser.getId());
        assertEquals("admin", actualUser.getUsername());
        assertNull(actualUser.getPassword());
        assertEquals("admin", actualUser.getFullname());
        assertEquals("ADMIN", actualUser.getRole());


    }
}

