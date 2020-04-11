package com.ranjay.models;

import lombok.Data;

@Data
public class MorningSession {
    private ConferenceEvent event;
    private int totalSessionDuration;
    private int currentDurationSession;
}