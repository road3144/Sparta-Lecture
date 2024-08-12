package com.market.payment;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PaymentEndpoint {

	@Value("${spring.application.name}")
	private String appName;

	@RabbitListener(queues = "${message.queue.payment}")
	public void receivePayment(String orderId) {
		log.info("Receive orderId : {}, ppName : {}", orderId, appName);
	}
}
