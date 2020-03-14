package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.dto.CreatedTrelloCardDto;
import com.crud.tasks.domain.dto.TrelloBoardDto;
import com.crud.tasks.domain.dto.TrelloCardDto;
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
public class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("A01", "Trello Board A01", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("A02", "Trello Board A02", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("A03", "Trello Board A03", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(trelloBoardDtos.size(), fetchedTrelloBoards.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Trello Card", "it's trello card", "top", "A01");
        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("1", "Trello Card", "url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdCardDto);

        //When
        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), newCard.getName());
    }
}
