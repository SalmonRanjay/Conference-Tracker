package com.ranjay;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ranjay.models.ConferenceEvent;
import com.ranjay.models.MorningSession;
import com.ranjay.service.SessionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionServiceTest {
    private MorningSession session;
    private SessionService sessionService;
    private ConferenceEvent event;
    private int sessionDuration ;


    @BeforeEach
    public void init(){
        session = new MorningSession();
        event = new ConferenceEvent("Test Event", 45);
        sessionService = new SessionService();
        

    }

    @Test
    public void canAddEventToSession(){
        sessionDuration = sessionService.addEventToSession(event, session.getEvents(), 180 , session.getCurrentDurationSession());
        assertTrue(sessionDuration > 0);
        assertNotNull(session.getEvents());
    }

    @Test
    public void eventSuccessfullyAddedToSession(){
        sessionDuration = sessionService.addEventToSession(event, session.getEvents(), 180 , session.getCurrentDurationSession());
        assertTrue(session.getEvents().size() > 0);
    }

    @Test
    public void cannotAddEventToSession(){
        session.setCurrentDurationSession(175);
        int sessionDuration = sessionService.addEventToSession(event, session.getEvents(), 180 , session.getCurrentDurationSession());

        assertTrue(sessionDuration < 0);
    }
}