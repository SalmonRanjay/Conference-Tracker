package com.ranjay.models;

import java.util.Iterator;
import java.util.List;

import com.ranjay.mediators.ISessionMediator;
import com.ranjay.mediators.ITrackMediator;
import com.ranjay.mediators.TrackMediator;
import com.ranjay.service.SessionService;

import lombok.Data;

@Data
public class Track {
    private Session morningSession;
    private Session afternoonSession;
    private ISessionMediator sessionMediator;
    private ITrackMediator trackMediator;

    public Track(ISessionMediator sessionMediator, ITrackMediator trackMediator){
        this.sessionMediator = sessionMediator;
        this.morningSession = new Session();
        this.afternoonSession = new Session();
        this.trackMediator = trackMediator;
    }

    public void loadTrackData(List<Event> events){
        SessionService service = new SessionService(this.sessionMediator);
        populateSession(events, service, this.morningSession,180,SessionType.MORNING);
        populateSession(events, service, this.afternoonSession,240,SessionType.AFTERNOON);
        this.trackMediator.setTrackFull(true);
    }

    private void populateSession(List<Event> events, SessionService service, Session session, int MaxSessionCapacity, SessionType sessionType) {
        Iterator<Event> iter = events.iterator();
        while (iter.hasNext()) {
            Event conferenceEvent = iter.next();
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