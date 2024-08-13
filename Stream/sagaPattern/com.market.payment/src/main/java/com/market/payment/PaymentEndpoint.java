package com.market.payment;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PaymentEndpoint {

	private final PaymentService paymentService;

	@RabbitListener(queues = "${message.queue.payment}")
	public void receiveMessage(DeliveryMessage deliveryMessage) {
		log.info("PAYMENT MESSAGE: {}", deliveryMessage);
		paymentService.createPayment(deliveryMessage);
	}
}
