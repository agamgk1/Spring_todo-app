package com.spring.course.controller;

import com.spring.course.TaskConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("/info/url")
    String url() {
        return dataSource.getUrl();
    }

    @GetMapping("/info/prop")
    boolean myProp() {
        return myProp.getTemplate().isAllowMultipleTasks();
    }
}
