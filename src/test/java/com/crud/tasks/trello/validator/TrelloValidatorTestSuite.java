package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.dto.TrelloCard;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloValidatorTestSuite {
    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void testValidateCard(){
        //Given
        Logger logger = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        TrelloCard trelloCardTest = new TrelloCard("test", "testtesttest", "top", "A01");
        TrelloCard trelloCard = new TrelloCard("Trello Care", "It's Trello Card", "botton", "A01");

        //When
        trelloValidator.validateCard(trelloCardTest);
        trelloValidator.validateCard(trelloCard);

        //Then
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("Someone is testing my application!", logsList.get(0).getMessage());
        assertEquals("Seems that my application is used in proper way.", logsList.get(1).getMessage());
    }

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "TrelloBoard", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Test", new ArrayList<>());
        TrelloBoard trelloBoard3 = new TrelloBoard("3", "My Board", new ArrayList<>());
        TrelloBoard trelloBoard4 = new TrelloBoard("4", "test", new ArrayList<>());
        TrelloBoard trelloBoard5 = new TrelloBoard("5", "Trello Board", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        trelloBoards.add(trelloBoard3);
        trelloBoards.add(trelloBoard4);
        trelloBoards.add(trelloBoard5);

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(3, filteredBoards.size());
    }
}
