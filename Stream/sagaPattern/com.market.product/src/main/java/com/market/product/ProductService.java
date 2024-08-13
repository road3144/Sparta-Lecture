package com.market.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	@Value("${message.queue.payment}")
	private String paymentQueue;

	@Value("${message.queue.err.order}")
	private String errOrderQueue;

	private final RabbitTemplate rabbitTemplate;

	public void reduceProductAmount(DeliveryMessage deliveryMessage) {
		Integer productId = deliveryMessage.getProductId();
		Integer productQuantity = deliveryMessage.getProductQuantity();

		if (productId != 1 || productQuantity > 1) {
			deliveryMessage.setErrorType("PRODUCT QUANTITY Exceeded");
			this.rollbackProduct(deliveryMessage);
			return;
		}

		rabbitTemplate.convertAndSend(paymentQueue, deliveryMessage);

	}

	public void rollbackProduct(DeliveryMessage deliveryMessage) {
		log.info("PRODUCT ROLLBACK");
		rabbitTemplate.convertAndSend(errOrderQueue, deliveryMessage);
	}
}
