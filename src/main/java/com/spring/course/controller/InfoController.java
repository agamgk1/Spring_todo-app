package com.spring.course.controller;

import com.spring.course.TaskConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/info")
class InfoController {
    // @value zasilenie pola propertisem. W klamrach nazwa propertisa
    //DataSourceProperties specjalna klsa do uzywania propertisow. Sama pobiera propertisy z .yml

    private DataSourceProperties dataSource;
    private TaskConfigurationProperties myProp;

    //wstrzykiwanie propertisow przez konstruktor
    InfoController(final DataSourceProperties dataSource, final TaskConfigurationProperties myProp) {
        this.dataSource = dataSource;
        this.myProp = myProp;
    }
    // w nawiasie rola z jaką można wejsc do danej metody (na dana strone)
    @Secured("ROLE_ADMIN")
    @GetMapping("/url")
    String url() {
        return dataSource.getUrl();
    }

    //to samo co @Security ale z pakietu java
    @RolesAllowed({"ROLE_USER, ROLE_ADMIN"})
    @GetMapping("/prop")
    boolean myProp() {
        return myProp.getTemplate().isAllowMultipleTasks();
    }
}
