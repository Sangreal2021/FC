package com.example.restapi;

import com.example.restapi.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

	// @Autowired
	//	- 스프링에서 관리하는 Bean들 중 자동으로 생성되는 오브젝트매퍼를 가져오겠다.
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() throws JsonProcessingException {

		var user = new UserRequest();
		user.setUserName("홍길동");
		user.setUserAge(10);
		user.setEmail("hong@gmail.com");
		user.setIsKorean(true);

		// 직렬화
		var json = objectMapper.writeValueAsString(user);
		System.out.println("직렬화 : " + json);

		// 역직렬화
		var dto = objectMapper.readValue(json, UserRequest.class);
		System.out.println("역직렬화 : " + dto);
	}

}
