package com.market.payment;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
	private UUID paymentId;
	private String userId;

	private Integer payAmount;

	private String payStatus;
}
