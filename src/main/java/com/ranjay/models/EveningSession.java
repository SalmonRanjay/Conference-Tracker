package com.ranjay.models;

import lombok.Data;

@Data
public class EveningSession {
    private ConferenceEvent event;
    private int totalSessionDuration;
    private int currentDurationSession;
}