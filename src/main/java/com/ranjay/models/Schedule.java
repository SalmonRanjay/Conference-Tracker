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
    private List<ArrayList<String>> conferenceScheduleByTrack = new ArrayList<>();
    public void addTrackToConference(Track track) {
        this.conferenceTracks.add(track);
    }

    public void setUpSchedule(){
      
        for (Track track : this.conferenceTracks) {
            Date currentDate = new Date();
            LocalDateTime localDateTime = LocalDate.now().atTime(8, 0);
            ArrayList <String> trackSchedule = new ArrayList<>();

            for (Event event : track.getMorningSession().getEvents()) {
                // convert LocalDateTime to date
                localDateTime = localDateTime.plusMinutes(event.getDuration());
                Date curentDatePlusSession = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                trackSchedule.add(dateFormat.format(curentDatePlusSession) + "\t " + event.getTitle());
              
            }
            // Add lunch to schedule
            // Date luncTime = Date.from(LocalDate.now().atTime(12, 0)).atZone(ZoneId.systemDefault()).toInstant();
            // trackSchedule.add(dateFormat.format( luncTime + "\t Lunch"));

            // update local date time variable to start after 1pm
            localDateTime = LocalDate.now().atTime(13, 0);
            for (Event event : track.getAfternoonSession().getEvents()) {
                // convert LocalDateTime to date
                Date curentDatePlusSession = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                trackSchedule.add(dateFormat.format(curentDatePlusSession) + "\t " + event.getTitle());
                localDateTime = localDateTime.plusMinutes(event.getDuration());
               
             
            }
            this.conferenceScheduleByTrack.add(trackSchedule);
        }
    }

    public void printSchedule(){
        for (String item : this.conferenceSchedule) {
            System.out.println(item + "\n");
        }
    }

    public void printScheduleByTrack(){
        int index = 1;
        for (ArrayList<String> list : this.conferenceScheduleByTrack) {
            System.out.println( "Track : " + index + " \n");
            for (String itemString : list) {
                System.out.println(itemString + "\n");
            }
            index++;
        }
    }
}