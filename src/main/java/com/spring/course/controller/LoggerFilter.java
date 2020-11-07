package com.spring.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//Adnotacja Order - interfejs springowy w ktorym definiuje sie kolejnosc filtrow w lancuchu
//@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class LoggerFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    //logika odpowiedzialna za filtrowanie
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            var httpRequest = (HttpServletRequest) request;
            logger.info("[doFilter]" + httpRequest.getMethod() +" "+ httpRequest.getRequestURI());
        }
        //wejscie w łańcuch procesowań
        chain.doFilter(request, response);

    }

}
