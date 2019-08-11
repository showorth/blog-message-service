package com.showorth.blogmessageservice.messageservice.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MessageController.class)
public class MessageControllerTest {
    private MockMvc mvc;

    @InjectMocks
    private MessageController messageController;


    @Before
    public void setUp() throws Exception {
        this.mvc =  MockMvcBuilders.standaloneSetup(messageController).build();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGreeting_default() throws Exception  {

        this.mvc.perform(get("/greeting")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome, World")));

    }

    @Test
    public void testGreeting_withName() throws Exception  {

        this.mvc.perform(get("/greeting")
                .param("name", "Stephanie")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome, Stephanie")));

    }
}
