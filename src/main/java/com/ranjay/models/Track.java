package com.ranjay.models;

import java.util.Iterator;
import java.util.List;

import com.ranjay.mediators.ISessionMediator;
import com.ranjay.service.SessionService;

import lombok.Data;

@Data
public class Track {
    private Session morningSession;
    private Session afternoonSession;
    private ISessionMediator sessionMediator;

    public Track(ISessionMediator sessionMediator){
        this.sessionMediator = sessionMediator;
        this.morningSession = new Session();
        this.afternoonSession = new Session();
    }

    public void loadTrackData(List<ConferenceEvent> events){
        SessionService service = new SessionService(this.sessionMediator);
        populateSession(events, service, this.morningSession,180,SessionType.MORNING);
        populateSession(events, service, this.afternoonSession,240,SessionType.AFTERNOON);
    }

    private void populateSession(List<ConferenceEvent> events, SessionService service, Session session, int MaxSessionCapacity, SessionType sessionType) {
        Iterator<ConferenceEvent> iter = events.iterator();
        while (iter.hasNext()) {
            ConferenceEvent conferenceEvent = iter.next();
            session.setCurrentDurationSession(service.addEventToSession(conferenceEvent, session.getEvents(), MaxSessionCapacity, session.getCurrentDurationSession()));
            if(sessionMediator.eventAddedSuccessfylly()){
                iter.remove();
                sessionMediator.setEventSuccessStatus(false);
            }
        }
        session.setType(sessionType);
        // for (ConferenceEvent conferenceEvent : events) {
        //     session.setCurrentDurationSession(service.addEventToSession(conferenceEvent, session.getEvents(), MaxSessionCapacity, session.getCurrentDurationSession()));
        //     if(sessionMediator.eventAddedSuccessfylly()){
        //         events.remove(conferenceEvent);
        //         sessionMediator.setEventSuccessStatus(false);
        //     }
        // }
    }
}   