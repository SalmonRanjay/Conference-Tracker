package com.ranjay.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Schedule {
    private static final String DATE_FORMAT = "EEEEE MMMMM yyyy HH:mm:ss";
    // private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private List<String> conferenceSchedule = new ArrayList<>();
    private List<Track> conferenceTracks = new ArrayList<>();

    public void addTrackToConference(Track track) {
        this.conferenceTracks.add(track);
    }

    public void setUpSchedule(){
        Date currentDate = new Date();
        LocalDateTime localDateTime = LocalDate.now().atTime(8, 0);
        // LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
    
        for (Track track : this.conferenceTracks) {
            for (Event event : track.getMorningSession().getEvents()) {
                // convert LocalDateTime to date
                localDateTime = localDateTime.plusMinutes(event.getDuration());
                Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                // System.out.println("\nOutput : " + dateFormat.format(currentDatePlusOneDay));
                this.conferenceSchedule.add(dateFormat.format(currentDatePlusOneDay));
            }
            for (Event event : track.getAfternoonSession().getEvents()) {
                // convert LocalDateTime to date
                localDateTime = localDateTime.plusMinutes(event.getDuration());
                Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                // System.out.println("\nOutput : " + dateFormat.format(currentDatePlusOneDay));
                this.conferenceSchedule.add(dateFormat.format(currentDatePlusOneDay));
            }
        }
    }

    public void printSchedule(){
        for (String item : this.conferenceSchedule) {
            System.out.println(item + "\n");
        }
    }
}