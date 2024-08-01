package com.spring_cloud.resilience4j.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable String id) {
		return productService.getProductDetails(id);
	}
}
