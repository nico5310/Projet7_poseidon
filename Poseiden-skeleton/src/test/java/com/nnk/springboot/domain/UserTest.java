package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

