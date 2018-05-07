package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testTaskMapper() {
        //Given
        Task task = new Task(1L, "test", "test");
        List<Task> tasks = Arrays.asList(task);

        //When
        TaskDto mapToTaskDto = taskMapper.mapToTaskDto(task);
        List<TaskDto> mapToTaskDtoList = taskMapper.mapToTaskDtoList(tasks);
        Task mapToTask = taskMapper.mapToTask(mapToTaskDto);

        //Then
        Assert.assertEquals("test", mapToTaskDto.getContent());
        Assert.assertEquals("test", mapToTaskDtoList.get(0).getTitle());
        Assert.assertEquals("test", mapToTask.getContent());
    }

    @Test
    public void testMapToBoard() {
        //Given
        TrelloListDto testTrelloListDto = new TrelloListDto("testTrelloDto", "1", true);
        List<TrelloListDto> trelloLists = Arrays.asList(testTrelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "trelloBoard", trelloLists);
        List<TrelloBoardDto> trelloBoardListDto = Arrays.asList(trelloBoardDto);

        //when
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardListDto);

        //then
        Assert.assertEquals(1, trelloBoardList.get(0).getLists().size());
        Assert.assertEquals(1, trelloBoardList.size());
        Assert.assertEquals("trelloBoard", trelloBoardList.get(0).getName());
    }

    @Test
    public void testMapToBoardDto() {
        //given
        TrelloList testTrelloList = new TrelloList("1", "testTrello", true);
        List<TrelloList> trelloLists = Arrays.asList(testTrelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "testBoards", trelloLists);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        //when
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //then
        Assert.assertEquals("1", trelloBoardDtoList.get(0).getId());
        Assert.assertEquals("testBoards", trelloBoardDtoList.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //given
        TrelloListDto trelloListDto = new TrelloListDto("1", "testTrelloDto", true);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto);

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //then
        Assert.assertEquals("1", trelloLists.get(0).getId());
        Assert.assertEquals("testTrelloDto", trelloLists.get(0).getName());
    }

    @Test
    public void testMapToListDto() {
        //given
        TrelloList trelloList = new TrelloList("1", "testTrelloList", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList);

        //when
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //then
        Assert.assertEquals("testTrelloList", trelloListsDto.get(0).getName());
    }

    @Test
    public void testMapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "descr", "pos1", "1");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        Assert.assertEquals("test", trelloCard.getName());
        Assert.assertEquals("descr", trelloCard.getDescription());
        Assert.assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("test", "descr", "pos1", "1");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        Assert.assertEquals("test", trelloCardDto.getName());
        Assert.assertEquals("descr", trelloCardDto.getDescription());
        Assert.assertEquals("1", trelloCardDto.getListId());
    }
}
