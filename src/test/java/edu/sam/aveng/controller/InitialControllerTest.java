package edu.sam.aveng.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class InitialControllerTest {
    @Test
    public void testHomePage() throws Exception {
        // Create an instance of HomeController
        InitialController controller = new InitialController();

        // standaloneSetup(): Load the instance of HomeController to MockMvcBuilders.
        // build(): Create a MockMvc instance.
        // mock - иммитировать
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Ask the MockMvc instance to:
        // 1. Perform a GET request for /.
        // 2. Expect "home" view name.

        mockMvc.perform(get("/")).andExpect(view().name("home"));

        // ?
        assertEquals("home", controller.home());
    }
}