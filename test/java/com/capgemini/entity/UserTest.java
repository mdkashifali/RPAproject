package com.capgemini.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getEmail() {
        User u = new User();
//        u.setEmail("mdkashif@gmail.com");
        assertEquals("mdkashif@gmail.com", u.getEmail());
    }
}