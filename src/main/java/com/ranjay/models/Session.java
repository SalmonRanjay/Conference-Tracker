package com.ranjay.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Session {
    private List<ConferenceEvent> events;
    private static final int TOTALSESSIONDURATION = 180;
    private int currentDurationSession;
    private SessionType type;

    public Session(){
        events = new ArrayList<>();
        currentDurationSession = 0;
    }
  
}