package com.kafka_sample.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String topic, String message, String key) {
		for (int i=0; i < 10; i++) {
			kafkaTemplate.send(topic, key, message + " " + i);
		}
	}
}
