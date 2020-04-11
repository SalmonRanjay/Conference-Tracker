package com.ranjay.models;

import lombok.Data;

@Data
public class ConferenceEvent {

    private String title;
    private int duration;

    public ConferenceEvent(String title, int duration){
        this.title = title;
        this.duration = duration;
    }

}