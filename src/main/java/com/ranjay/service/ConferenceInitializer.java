package com.ranjay.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ranjay.models.ConferenceEvent;

public class ConferenceInitializer {
    // From Java 8 to Java 12 l​ightning
    public List<ConferenceEvent> initializeEVents(String filePath) {

        List<ConferenceEvent> events = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath); BufferedReader br = new BufferedReader(reader)) {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 11) {
                    String durationText = line.substring(line.length() - 6).substring(0, 3).trim();
                    String title = line.substring(0, line.indexOf("min") - 3);
                    Integer duration = Integer.parseInt(durationText);
                    events.add(new ConferenceEvent(title, duration));
                }
              
            }
            
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        catch( NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return events;
    }
}