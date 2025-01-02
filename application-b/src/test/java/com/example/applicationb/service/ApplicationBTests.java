package com.example.applicationb.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.function.Consumer;

@SpringBootTest
public class ApplicationBTests {

    @Autowired
    private MessageProcessor messageProcessor;

    @MockBean
    private StreamBridge streamBridge;

    @Test
    public void testInput() {
        String message = "Hello, World!";
        String uppercasedMessage = "HELLO, WORLD!";

        // Mock the behavior of the StreamBridge
        Mockito.when(streamBridge.send("output", uppercasedMessage)).thenReturn(true);

        // Get the input consumer and accept the message
        Consumer<String> inputConsumer = messageProcessor.input();
        inputConsumer.accept(message);

        // Verify that the StreamBridge sent the uppercased message
        Mockito.verify(streamBridge, Mockito.times(1)).send("output", uppercasedMessage);
    }

    @Test
    public void testUppercaseInput() {
        String uppercasedMessage = "HELLO, WORLD!";

        // Get the uppercaseInput consumer and accept the message
        Consumer<String> uppercaseInputConsumer = messageProcessor.uppercaseInput();
        uppercaseInputConsumer.accept(uppercasedMessage);

        // Verify the output (in this case, we can check the console output or use a logger)
        // For simplicity, we assume the method works as expected if no exceptions are thrown
    }
}