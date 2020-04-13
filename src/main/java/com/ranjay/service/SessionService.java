package com.ranjay.service;

import java.util.List;

import com.ranjay.mediators.ISessionMediator;
import com.ranjay.models.ConferenceEvent;

public class SessionService {

    private ISessionMediator sessionMediator;

    public SessionService(ISessionMediator sessionMediator){
        this.sessionMediator = sessionMediator;
    }

    public int addEventToSession(ConferenceEvent event, List<ConferenceEvent> events, int TOTALSESSIONDURATION, int currentDurationSession){
        int remainingSessionCapacity = TOTALSESSIONDURATION -  currentDurationSession  ;
        boolean isSessionFull = remainingSessionCapacity < event.getDuration();

        if( !isSessionFull ){
            // add session to events list
            events.add(event);
            currentDurationSession += event.getDuration();
            sessionMediator.setEventSuccessStatus(true);
        }
        else {
            // Session is full signal to mediator to start using evening session
            System.out.println("Session is full");
        }
        return currentDurationSession;
    }
}