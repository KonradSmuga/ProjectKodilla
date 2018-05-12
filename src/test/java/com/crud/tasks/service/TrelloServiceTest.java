package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TrelloClient trelloClient;



    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "name", false));

        List<TrelloBoardDto> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoardDto("1", "name", trelloList));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoard);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoardDto.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "google.pl");
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("google.pl", createdTrelloCardDto.getShortUrl());
    }
}