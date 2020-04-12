package com.ranjay.service;

import java.util.List;

import com.ranjay.models.ConferenceEvent;

public class SessionService {

    public int addEventToSession(ConferenceEvent event, List<ConferenceEvent> events, int TOTALSESSIONDURATION, int currentDurationSession){
        int isSessionFull = TOTALSESSIONDURATION - currentDurationSession;
        if( isSessionFull > 0){
            // add session to events list
            events.add(event);
            currentDurationSession += event.getDuration();
        }
        else if( isSessionFull <= 0 ){
            // Session is full signal to mediator to start using evening session
            System.out.println("Session is full");
        }
        return currentDurationSession;
    }
}