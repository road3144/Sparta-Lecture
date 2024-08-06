package com.example.redis.repo;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionController {

	@GetMapping("/set")
	public String set(
		@RequestParam String p,
		HttpSession session
	) {
		session.setAttribute("p", p);
		return "Saved: " + p;
	}

	@GetMapping("/get")
	public String get(
		HttpSession session
	) {
		return String.valueOf(session.getAttribute("p"));
	}
}
