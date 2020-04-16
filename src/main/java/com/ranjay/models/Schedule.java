package com.ranjay.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Schedule {
    private static final String DATE_FORMAT = "EEEEE MMMMM yyyy HH:mm:ss";
   
    private DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    

   
    private List<Track> conferenceTracks = new ArrayList<>();
    private List<ArrayList<String>> conferenceScheduleByTrack = new ArrayList<>();

    public void addTrackToConference(Track track) {
        this.conferenceTracks.add(track);
    }

    // Could be refactored into less lines
    public void setUpSchedule() {

        for (Track track : this.conferenceTracks) {

            LocalDateTime localDateTime = LocalDate.now().atTime(9, 0);
            ArrayList<String> trackSchedule = new ArrayList<>();

            populateSchedule(track.getMorningSession().getEvents(), localDateTime, trackSchedule);
            // Add lunch to schedule -> need to refacto to proper way of adding lunch
            LocalDateTime lunchDateTime = LocalDate.now().atTime(12, 0);
            Date luncTime = Date.from(lunchDateTime.atZone(ZoneId.systemDefault()).toInstant());
            trackSchedule.add(dateFormat.format(luncTime) + "\t " + "Lunch");

            // update local date time variable to start after 1pm
            localDateTime = LocalDate.now().atTime(13, 0);
            populateSchedule(track.getAfternoonSession().getEvents(), localDateTime, trackSchedule);
           
            // Checking if Current Time Increments are after 4 then set time to 5 else set time to 4
            LocalDateTime afterNoonSession = LocalDate.now().atTime(16, 0);
            LocalDateTime networkingSession = null;
            Date networkingTime = null;

            if (localDateTime.isAfter(afterNoonSession)) {
                 networkingSession = LocalDate.now().atTime(17, 0);
                 networkingTime = Date.from(networkingSession.atZone(ZoneId.systemDefault()).toInstant());
                
            }else{
                networkingSession = LocalDate.now().atTime(16, 0);
                 networkingTime = Date.from(networkingSession.atZone(ZoneId.systemDefault()).toInstant());
            }
            trackSchedule.add(dateFormat.format(networkingTime) + "\t " + "Networking Event");
            this.conferenceScheduleByTrack.add(trackSchedule);
        }
    }

    private void populateSchedule(List<Event> session, LocalDateTime localDateTime, ArrayList<String> trackSchedule) {
        for (Event event : session) {
            // convert LocalDateTime to date
            Date curentDatePlusSession = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            trackSchedule.add(dateFormat.format(curentDatePlusSession) + "\t " + event.getTitle());
            localDateTime = localDateTime.plusMinutes(event.getDuration());
        }
    }

    public void printScheduleByTrack() {
        int index = 1;
        for (ArrayList<String> list : this.conferenceScheduleByTrack) {
            System.out.println("Track : " + index + " \n");
            for (String itemString : list) {
                System.out.println(itemString + "\n");
            }
            index++;
        }
    }
}