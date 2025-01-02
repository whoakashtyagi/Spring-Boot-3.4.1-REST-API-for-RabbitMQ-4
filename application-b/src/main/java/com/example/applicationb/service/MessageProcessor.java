package com.example.applicationb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class MessageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    @Bean
    public Function<String, String> processor() {
        return message -> {
            logger.info("Received message from incoming: {}", message);
            String uppercasedMessage = message.toUpperCase();
            logger.info("Sent message to uppercase: {}", uppercasedMessage);
            return uppercasedMessage;
        };
    }

    @Bean
    public Consumer<String> output() {
        return message -> {
            logger.info("Received message from uppercase: {}", message);
            logger.info("Message processed successfully: {}", message);
        };
    }




}