package com.spring.course.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@retention - jak długo ta adnotacja ma pozostawac
//@Target - umozliwaia konfiguracje adnotacji - co mozemy adnotowac tą adnotacją
//.RUNTIME - jest zachowana rwniez wtedy jak aplikacja jest uruchomiona
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface IllegalExceptionProcessing {

}
