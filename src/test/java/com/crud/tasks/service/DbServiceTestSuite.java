package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Task 1", "Task 1 content"));
        taskList.add(new Task(2L, "Task 2", "Task 2 content"));
        taskList.add(new Task(3L, "Task 3", "Task 3 content"));
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> newTaskList = dbService.getAllTasks();

        //Then
        assertEquals(3, newTaskList.size());
        assertEquals("Task 1", newTaskList.get(0).getTitle());
        assertEquals("Task 2 content", newTaskList.get(1).getContent());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Task", "Task content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task newTask = dbService.saveTask(task);

        //Then
        assertEquals("Task", newTask.getTitle());
        assertEquals("Task content", newTask.getContent());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task1 = new Task(1L, "Task #1", "Task #1 content");
        when(repository.findById(task1.getId())).thenReturn(Optional.of(task1));

        //When
        Optional<Task> foundTask = dbService.getTask(task1.getId());

        //Then
        assertEquals("Task #1", foundTask.get().getTitle());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task1 = new Task(1L, "Task #1", "Task #1 content");
        doNothing().when(repository).deleteById(task1.getId());

        //When
        dbService.deleteTask(task1.getId());

        //Then
        verify(repository, times(1)).deleteById(task1.getId());
    }
}
