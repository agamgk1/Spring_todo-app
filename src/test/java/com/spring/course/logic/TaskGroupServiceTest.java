package com.spring.course.logic;

import com.spring.course.model.TaskGroup;
import com.spring.course.model.TaskGroupRepository;
import com.spring.course.model.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskGroupServiceTest {
    @Test
    @DisplayName("should throw when undone tasks")
    void toggleGroup_undoneTasks_throwsIllegalStateException() {
        //given
        var mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(true);
        //system under tests
        var toTest = new TaskGroupService(null, mockTaskRepository);
       //whrn
        var exception = catchThrowable(() -> toTest.toggleGroup(2));
        //then
        assertThat(exception).isInstanceOf(IllegalStateException.class).hasMessageContaining("Group has undone tasks");
    }
    @Test
    @DisplayName("Should throw when no undone but undone group")
    void toggleGroup_wrongId_throwsIllegalArgumentException() {
        //given
        var mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        //and
        var mockTaskGroupRepository = mock(TaskGroupRepository.class);
        when(mockTaskGroupRepository.findById(anyInt())).thenReturn(Optional.empty());
        //system under test
        var toTest = new TaskGroupService(mockTaskGroupRepository, mockTaskRepository);
        //when
        var exception = catchThrowable(() -> toTest.toggleGroup(2));
        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("TaskGroup with given Id not found");
    }
    @Test
    @DisplayName("Should toggle group")
    void toggleGroup_worksAsExpected() {
        //given
        var mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        //and
        var taskGroup = new TaskGroup();
        var beforeToggle = taskGroup.isDone();
        //and
        var mockTaskGroupRepository = mock(TaskGroupRepository.class);
        when(mockTaskGroupRepository.findById(anyInt())).thenReturn(Optional.of(taskGroup));
        //system under test
        var toTest = new TaskGroupService(mockTaskGroupRepository, mockTaskRepository);
        //when
        toTest.toggleGroup(0);
        //then
        assertThat(taskGroup.isDone()).isEqualTo(!beforeToggle);
    }
}
