package com.demo.userservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceApplicationTests {
	@Mock
	private SpringApplicationBuilder springApplicationBuilder;
	@Test
	void contextLoads() {
		Mockito.when(springApplicationBuilder.sources(UserServiceApplication.class)).thenReturn(springApplicationBuilder);
		assertTrue(true);
	}

}
