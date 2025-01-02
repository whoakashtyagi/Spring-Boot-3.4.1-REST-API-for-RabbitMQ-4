package com.example.applicationa.controller;

import com.example.applicationa.controller.MessageController;
import com.example.applicationa.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class ApplicationATests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void testPostMessage() throws Exception {
        String message = "Hello, World!";
        Mockito.doNothing().when(messageService).sendMessage(message);

        mockMvc.perform(post("/message")
                        .content(message)
                        .contentType("application/json"))
                .andExpect(status().isOk());

        Mockito.verify(messageService, Mockito.times(1)).sendMessage(message);
    }
}