package com.spring.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// adnotacja ktora pozwala na obsługę błędów z innych kontrolerów, które będzą oznaczone adnotacja IllegalExceptionProcessing. Każdy kontroler w ktorym pojawi sie illegalStateException
// będzie obsłużony tutaj
@RestControllerAdvice(annotations = IllegalExceptionProcessing.class)
class IllegalExceptionControllerAdvice {

    //Adnotacja do obsługi wyjatkow przez springa (procesowanie)
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> handleIllegalState(IllegalStateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
