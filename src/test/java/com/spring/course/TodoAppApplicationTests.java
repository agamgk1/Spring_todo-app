package com.spring.course;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// dzięki tej adnotacji faktycznie uruchamia sie cala aplikacjia (caly konteks springowy)
@SpringBootTest
class TodoAppApplicationTests {

	//testo ładujący kontekst springowy - test bazowy
	@Test
	void contextLoads() {
	}


}
