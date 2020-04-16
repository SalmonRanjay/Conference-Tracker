package com.ranjay.service;

import java.util.List;

import com.ranjay.mediators.ISessionMediator;
import com.ranjay.models.Event;

public class SessionService {

    private ISessionMediator sessionMediator;

    public SessionService(ISessionMediator sessionMediator){
        this.sessionMediator = sessionMediator;
    }

    public int addEventToSession(Event event, List<Event> events, int TOTALSESSIONDURATION, int currentDurationSession){
        int remainingSessionCapacity = TOTALSESSIONDURATION -  currentDurationSession  ;
        boolean isSessionFull = remainingSessionCapacity < event.getDuration();

        if( !isSessionFull ){
            // add session to events list
            if( (TOTALSESSIONDURATION == 180) && (event.getDuration() == 5) ){
                // skip adding to morning session
                return currentDurationSession;
            }else{
                events.add(event);
                currentDurationSession += event.getDuration();
                sessionMediator.setEventSuccessStatus(true);
            }
        }
        else {
           
            sessionMediator.setEventSuccessStatus(false);
        }
        return currentDurationSession;
    }
}