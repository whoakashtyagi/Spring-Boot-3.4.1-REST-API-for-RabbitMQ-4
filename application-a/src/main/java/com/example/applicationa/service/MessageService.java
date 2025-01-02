package com.example.applicationa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final StreamBridge streamBridge;

    public MessageService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendMessage(String message) {
        logger.info("Sending message: {}", message);
        streamBridge.send("output", message);
        logger.info("Sent message: {}", message);
    }


}
