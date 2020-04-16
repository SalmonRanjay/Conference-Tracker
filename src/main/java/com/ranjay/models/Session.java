package com.ranjay.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Session {
    // need to refactor to prevent escaping reference
    private List<Event> events;
    private static final int TOTALSESSIONDURATION = 180;
    private int currentDurationSession;
    private SessionType type;

    public Session(){
        events = new ArrayList<>();
        currentDurationSession = 0;
    }
    
    // used to prevent escaping reference
    // public List<Event> getEvents(){
    //     return Collections.unmodifiableList(this.events);
    // }
}