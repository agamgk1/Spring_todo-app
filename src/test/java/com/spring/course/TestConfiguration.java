package com.spring.course;

import com.spring.course.model.Task;
import com.spring.course.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.*;

@Configuration
class TestConfiguration {



    // @Primary Adnotacja ta apowoduje ze repozytorium zdefiniowane w tescie jest glowne i zawsze bedzie uzywane w tescie
   // @ConditionalOnMissingBean te repozytorium obowiazuje tylko wtedy jezeli nie ma zdefiniowanego zadnego innego repozytorium
        // @Profile("integration) - powoduje ze tylko jak wlaczymy aplikacje z profilem integration stworzy sie task repozitory ponizej
    //w przeciwnym wypadku odpali sie standardowe repozytorium czyli sqlTasksRepozitory
    // @primary konieczne do tego aby ten bean stal sie beanem glownym
    //DataSource konfiguracja do zrodla danych
  @Bean
  @Primary
  @Profile("!integration")
    DataSource e2eTestDataSource() {
      var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
      result.setDriverClassName("org.h2.Driver");
      return result;
  }

   @Bean
   @Primary
   @Profile("integration")
   TaskRepository testRepo() {
        return new TaskRepository() {
            private Map<Integer, Task> tasks = new HashMap<>();

            @Override
            public List<Task> findAll() {
                return new ArrayList<>(tasks.values());
            }

            @Override
            public Optional<Task> findById(Integer id) {
                return Optional.ofNullable(tasks.get(id));
            }

            @Override
            public boolean existsById(Integer id) {
                return tasks.containsKey(id);
            }

            @Override
            public boolean existsByDoneIsFalseAndGroup_Id(Integer groupId) {
                return false;
            }

            @Override
            public Task save(Task entity) {
                 int key = tasks.size() + 1;

            //DOSTEP PRZEZ REFLEKSJE
                try {
                    var field =  Task.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                 tasks.put(key, entity);
                 return tasks.get(key);
            }

            @Override
            public Page<Task> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public List<Task> findByDone(boolean state) {
                return null;
            }

            @Override
            public List<Task> findAllByGroup_Id(Integer groupId) {
                return List.of();
            }
        };
    }

}
