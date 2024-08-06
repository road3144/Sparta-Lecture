package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTemplateTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void stringOpsTest() {
		ValueOperations<String, String> ops
			//RedisTemplate에 설정된 타입을 바탕으로 Redis 조장
			= stringRedisTemplate.opsForValue();
		ops.set("simplekey", "simplevalue");
		System.out.println(ops.get("simplekey"));
		ops.set("greeting", "hello redis!");
		System.out.println(ops.get("greeting"));
	}

	@Test
	public void stringSetOpsTest() {
		SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
		setOps.add("hobbies", "games");
		setOps.add("hobbies", "coding");
		setOps.add("hobbies", "alcohol");
		setOps.add("hobbies", "games");
		System.out.println(setOps.size("hobbies"));
	}

	@Autowired
	private RedisTemplate<String, ItemDto> itemRedisTemplate;

	@Test
	public void itemRedisTemplateTest() {
		ValueOperations<String, ItemDto> itemDtoOps
			= itemRedisTemplate.opsForValue();
		itemDtoOps.set("my:keyboard", ItemDto.builder()
			.name("Mechanical Keyboard")
			.price(25000)
			.description("omg too expensive")
			.build());
		System.out.println(itemDtoOps.get("my:keyboard"));
	}

}