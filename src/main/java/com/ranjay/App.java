package com.ranjay;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ranjay.mediators.ISessionMediator;
import com.ranjay.mediators.ITrackMediator;
import com.ranjay.mediators.SessionMediator;
import com.ranjay.mediators.TrackMediator;
import com.ranjay.models.Event;
import com.ranjay.models.Schedule;
import com.ranjay.models.Track;
import com.ranjay.service.ConferenceInitializer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        ConferenceInitializer initializer = new ConferenceInitializer();
        List<Event> events = initializer.initializeEVents("input.txt");
        ISessionMediator sessionMediator = new SessionMediator();
        ITrackMediator trackMediator = new TrackMediator();

        Schedule conferenceSchedule = new Schedule();

        Track firstTrack = new Track(sessionMediator, trackMediator);
        firstTrack.loadTrackData(events);
        conferenceSchedule.addTrackToConference(firstTrack);

        Iterator<Event> iter = events.iterator();
        do {
            if (trackMediator.isTrackFull()) {
                trackMediator.setTrackFull(false);
                Track track = new Track(sessionMediator, trackMediator);
                track.loadTrackData(events);
                conferenceSchedule.addTrackToConference(track);
            }

        } while (iter.hasNext());
        
        conferenceSchedule.setUpSchedule();
        conferenceSchedule.printScheduleByTrack();
    }
}
