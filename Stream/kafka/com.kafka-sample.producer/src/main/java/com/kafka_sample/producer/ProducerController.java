package com.kafka_sample.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProducerController {

	private final ProducerService producerService;

	@GetMapping("/send")
	public String sendMessage(
		@RequestParam("topic") String topic,
		@RequestParam("message") String message,
		@RequestParam("key") String key
	) {
		producerService.sendMessage(topic, message, key);
		return "Message send to kafka topic";
	}
}
