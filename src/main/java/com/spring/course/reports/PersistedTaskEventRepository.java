package com.spring.course.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//integer bo id jest typu int
interface PersistedTaskEventRepository extends JpaRepository<PersistedTaskEvent, Integer> {
    List<PersistedTaskEvent> findByTaskId(int taskId);
}