package com.arpon007.netflixclone.auth.service;

import com.arpon007.netflixclone.auth.event.UserSignupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, UserSignupEvent> kafkaTemplate;
    private static final String TOPIC = "user-signup";

    public void publishUserSignupEvent(UserSignupEvent event) {
        try {
            kafkaTemplate.send(TOPIC, event);
            log.info("Published user signup event for email: {}", event.getEmail());
        } catch (Exception e) {
            log.error("Failed to publish user signup event for email: {}", event.getEmail(), e);
        }
    }
}
