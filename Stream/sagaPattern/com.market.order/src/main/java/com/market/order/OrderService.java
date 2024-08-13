package com.market.order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

	@Value("${message.queue.product}")
	private String productQueue;

	private final RabbitTemplate rabbitTemplate;

	private Map<UUID, Order> orderStore = new HashMap<>();

	public Order createOrder(OrderEndpoint.OrderRequestDto requestDto) {
		Order order = requestDto.toOrder();
		orderStore.put(order.getOrderId(), order);

		DeliveryMessage deliveryMessage = requestDto.toDeliveryMessage(order.getOrderId());
		rabbitTemplate.convertAndSend(productQueue, deliveryMessage);

		return order;
	}

	public Order getOrder(UUID orderId) {
		return orderStore.get(orderId);
	}

	public void rollbackOrder(DeliveryMessage deliveryMessage) {
		Order order = orderStore.get(deliveryMessage.getOrderId());
		order.cancelOrder(deliveryMessage.getErrorType());
	}
}
