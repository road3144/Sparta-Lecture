package com.service.a;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AController {

	private final BServiceClient bServiceClient;

	@GetMapping("/hi")
	public String hello() {
		String hello = bServiceClient.getHello();
		return "sevice-a: hi ###### service-b: " + hello;
	}
}