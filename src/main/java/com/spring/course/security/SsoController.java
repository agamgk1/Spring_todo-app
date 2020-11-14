package com.spring.course.security;

//Sso - jedne logowanie do wielu servisów

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
class SsoController {
    //metoda służąca do wylogowania (wchodzimy na stronę /logout i następuje wylogowanie i powrót do strony startowej)
    @GetMapping("/logout")
    String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }
}
