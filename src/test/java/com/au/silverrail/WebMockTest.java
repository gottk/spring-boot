package com.au.silverrail;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.au.silverrail.controller.UserController;
import com.au.silverrail.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    public void stateShouldReturnMessageFromService() throws Exception {
    	
        //when(service.getState(0)).thenReturn("");        
        
        mockMvc.perform(get("/state")).andDo(print())
        		.andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }
}