package com.example.BalantaTaller1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.BalantaTaller1.main.BalantaTaller1Application;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()
class WorkorderTest {

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}

	@BeforeEach
	void setUp() {
		
	}

	@Nested
	@DisplayName("Save methods")
	class Workordersave{
		@Test
		void test() {
			fail("Not yet implemented");
		}
	}
	
	
	@AfterEach
	void tearDown() {
	}
	
	@AfterAll
	static void end() {
		System.out.println("... TEST FINISHED ...");
	}


}
