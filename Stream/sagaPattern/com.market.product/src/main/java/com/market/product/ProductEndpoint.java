package com.market.product;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductEndpoint {

	private final ProductService productService;

	@RabbitListener(queues = "${message.queue.product}")
	public void receiveMessage(DeliveryMessage deliveryMessage) {
		log.info("PRODUCT RECEIVE: {}", deliveryMessage.toString());
		productService.reduceProductAmount(deliveryMessage);
	}

	@RabbitListener(queues = "${message.queue.err.product}")
	public void receiveErrorMessage(DeliveryMessage deliveryMessage) {
		log.info("PRODUCT RECEIVE ERROR");
		productService.rollbackProduct(deliveryMessage);
	}
}
