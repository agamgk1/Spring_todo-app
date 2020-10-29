package com.spring.course;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//Wlasna klasa z propertisami
// musza byc dwie adnotacje
// adnotacja ConfigurationProperties wskazuje ze jest to klasa z propertisami. Musi byc prefiks czyli w tym wypadku "task" z nazwy propertisu w .yml
//dodatkowo trzeba dodac dependency spring-boot-configuration-processor do mavena (moze byc nikonieczne)
@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
   private Template template;

    public Template getTemplate() {
        return template;
    }
    public void setTemplate(final Template template) {
        this.template = template;
    }

    //klasa pomocnicza zeby dostac sie do propertisa - nie trzeba tego uzywac
   public static class Template {
        private boolean allowMultipleTasks;

         public boolean isAllowMultipleTasks() {
            return allowMultipleTasks;
        }
       public void setAllowMultipleTasks(final boolean allowMultipleTasks) {
            this.allowMultipleTasks = allowMultipleTasks;
        }
    }
}
