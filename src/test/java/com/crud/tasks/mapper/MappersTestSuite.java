package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.dto.TaskDto;
import com.crud.tasks.domain.dto.TrelloBoardDto;
import com.crud.tasks.domain.dto.TrelloListDto;
import org.junit.Assert;
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

    @Test
    public void testMapToBoards() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloListDto list1 = new TrelloListDto("01ABC", "List 01ABC", false);
        TrelloListDto list2 = new TrelloListDto("02ABC", "List 02ABC", false);
        TrelloListDto list3 = new TrelloListDto("03ABC", "List 03ABC", true);
        TrelloListDto list4 = new TrelloListDto("01DEF", "List 01DEF", false);
        TrelloListDto list5 = new TrelloListDto("02DEF", "List 02DEF", false);
        TrelloListDto list6 = new TrelloListDto("03DEF", "List 03DEF", false);
        TrelloListDto list7 = new TrelloListDto("01GHI", "List 01GHI", true);
        TrelloListDto list8 = new TrelloListDto("02GHI", "List 02GHI", true);
        TrelloBoardDto board1 = new TrelloBoardDto("TB01", "Trello Board #1", new ArrayList<>());
        TrelloBoardDto board2 = new TrelloBoardDto("TB02", "Trello Board #2", new ArrayList<>());
        TrelloBoardDto board3 = new TrelloBoardDto("TB03", "Trello Board #3", new ArrayList<>());
        board1.getLists().add(list1);
        board1.getLists().add(list2);
        board1.getLists().add(list3);
        board2.getLists().add(list4);
        board2.getLists().add(list5);
        board2.getLists().add(list6);
        board3.getLists().add(list7);
        board3.getLists().add(list8);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(board1);
        trelloBoardDtoList.add(board2);
        trelloBoardDtoList.add(board3);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(3, trelloBoardList.size());
        assertEquals(3, trelloBoardList.get(0).getLists().size());
        assertEquals(3, trelloBoardList.get(1).getLists().size());
        assertEquals(2, trelloBoardList.get(2).getLists().size());
        assertEquals(list1.getId(), trelloBoardList.get(0).getLists().get(0).getId());
        assertEquals(list4.getId(), trelloBoardList.get(1).getLists().get(0).getId());
        assertEquals(list7.getId(), trelloBoardList.get(2).getLists().get(0).getId());
    }

    @Test
    public void testMapToBoardsDto() {
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloList list1 = new TrelloList("01ABC", "List 01ABC", false);
        TrelloList list2 = new TrelloList("02ABC", "List 02ABC", false);
        TrelloList list3 = new TrelloList("03ABC", "List 03ABC", true);
        TrelloList list4 = new TrelloList("01DEF", "List 01DEF", false);
        TrelloList list5 = new TrelloList("02DEF", "List 02DEF", false);
        TrelloList list6 = new TrelloList("03DEF", "List 03DEF", false);
        TrelloList list7 = new TrelloList("01GHI", "List 01GHI", true);
        TrelloList list8 = new TrelloList("02GHI", "List 02GHI", true);
        TrelloBoard board1 = new TrelloBoard("TB01", "Trello Board #1", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("TB02", "Trello Board #2", new ArrayList<>());
        TrelloBoard board3 = new TrelloBoard("TB03", "Trello Board #3", new ArrayList<>());
        board1.getLists().add(list1);
        board1.getLists().add(list2);
        board1.getLists().add(list3);
        board2.getLists().add(list4);
        board2.getLists().add(list5);
        board2.getLists().add(list6);
        board3.getLists().add(list7);
        board3.getLists().add(list8);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(board1);
        trelloBoardList.add(board2);
        trelloBoardList.add(board3);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(3, trelloBoardDtoList.size());
        assertEquals(3, trelloBoardDtoList.get(0).getLists().size());
        assertEquals(3, trelloBoardDtoList.get(1).getLists().size());
        assertEquals(2, trelloBoardDtoList.get(2).getLists().size());
        assertEquals(list1.getId(), trelloBoardDtoList.get(0).getLists().get(0).getId());
        assertEquals(list4.getId(), trelloBoardDtoList.get(1).getLists().get(0).getId());
        assertEquals(list7.getId(), trelloBoardDtoList.get(2).getLists().get(0).getId());
    }
}
