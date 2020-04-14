package com.ranjay;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ranjay.mediators.SessionMediator;
import com.ranjay.models.Event;
import com.ranjay.models.Session;
import com.ranjay.service.SessionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionServiceTest {
    private Session session;
    private SessionService sessionService;
    private Event event;
    private int sessionDuration ;


    @BeforeEach
    public void init(){
        session = new Session();
        event = new Event("Test Event", 45);
        sessionService = new SessionService(new SessionMediator());
        

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

    // @Test
    // public void cannotAddEventToSession(){
    //     session.setCurrentDurationSession(175);
    //     int sessionDuration = sessionService.addEventToSession(event, session.getEvents(), 180 , session.getCurrentDurationSession());

    //     assertTrue(sessionDuration < 0);
    // }
}