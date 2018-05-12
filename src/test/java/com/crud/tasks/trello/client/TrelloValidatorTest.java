package com.crud.tasks.trello.client;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;


    @Test
    public void testValidateTrelloBoards() {


        //Given
        List<TrelloList> trelloLists = new ArrayList<>();

        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "name", trelloLists),
                new TrelloBoard("2", "name1", trelloLists)
        );

        //When
        List<TrelloBoard> boards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assert.assertEquals("name1", boards.get(1).getName());
        Assert.assertEquals("1", boards.get(0).getId());
        Assert.assertEquals(2, boards.size());
}

    @Test
    public void testValidateCard() {
        //given
        TrelloCard trelloCard = new TrelloCard("test", "desc", "pos", "1");

        //when
        trelloValidator.validateCard(trelloCard);

        //then
        Assert.assertEquals("test", trelloCard.getName());
        Assert.assertEquals("pos", trelloCard.getPos());
    }
}