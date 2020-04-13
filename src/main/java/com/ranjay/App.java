package com.ranjay;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ranjay.mediators.ISessionMediator;
import com.ranjay.mediators.SessionMediator;
import com.ranjay.models.ConferenceEvent;
import com.ranjay.models.Track;
import com.ranjay.service.ConferenceInitializer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        ConferenceInitializer initializer = new ConferenceInitializer();
        List<ConferenceEvent> events = initializer.initializeEVents("input.txt");
        ISessionMediator sessionMediator = new SessionMediator();
        // Collections.sort(events);
        // create track one object
        Track firstTrack = new Track(sessionMediator);
        firstTrack.loadTrackData(events);

        System.out.println(" \n \n ============ Morning SESSION ======================");
        for (ConferenceEvent conferenceEvent : firstTrack.getMorningSession().getEvents()) {
            System.out.println(conferenceEvent.toString());
        }
        System.out.println(" \n \n ============ EVENING SESSION ======================");
        for (ConferenceEvent conferenceEvent : firstTrack.getAfternoonSession().getEvents()) {
            System.out.println(conferenceEvent.toString());
        }
        
        // create track two object

        // iterate over list fill track one object, filling morning session first then afternoon session

        // delete item from list once it's added to a session
    }
}
