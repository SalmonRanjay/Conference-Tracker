package com.ranjay.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MorningSession {
    private List<ConferenceEvent> events;
    private static final int TOTALSESSIONDURATION = 180;
    private int currentDurationSession;

    public MorningSession(){
        events = new ArrayList<>();
        currentDurationSession = 0;
    }
  
}