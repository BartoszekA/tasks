package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.dto.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappersTestSuite {
    @Test
    public void testMapToTask() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "Task1", "This is task #1");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId());
        assertEquals("Task1", task.getTitle());
        assertEquals("This is task #1", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(2L, "Task2", "This is task #2");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(2L, taskDto.getId());
        assertEquals("Task2", taskDto.getTitle());
        assertEquals("This is task #2", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task1 = new Task(1L, "Task1", "This is task #1");
        Task task2 = new Task(2L, "Task2", "This is task #2");
        Task task3 = new Task(3L, "Task3", "This is task #3");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(3, taskDtoList.size());
        assertEquals(1L, taskDtoList.get(0).getId());
        assertEquals("Task2", taskDtoList.get(1).getTitle());
        assertEquals("This is task #3", taskDtoList.get(2).getContent());
    }
}
