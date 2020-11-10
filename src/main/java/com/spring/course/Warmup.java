package com.spring.course;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//Klasa służąca do zaapylania przykładowych danych, do tworzenia catcha
//ContextRefreshedEvent - w momencie gdy kontrkst springa jest juz odswieżony robimy jakas akcje
@Component
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
