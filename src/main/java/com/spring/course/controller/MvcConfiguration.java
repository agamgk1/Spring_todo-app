package com.spring.course.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Set;

@Configuration
class MvcConfiguration implements WebMvcConfigurer {
    private Set<HandlerInterceptor> interceptors;

    MvcConfiguration(Set<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //dodanie wszystkich interceptorow do kontekstu springowego
        interceptors.stream().forEach(registry::addInterceptor);

    }
}
