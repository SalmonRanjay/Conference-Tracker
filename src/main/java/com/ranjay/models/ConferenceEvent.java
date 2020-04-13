package com.ranjay.models;

import lombok.Data;

@Data
public class ConferenceEvent implements Comparable {

    private String title;
    private int duration;

    public ConferenceEvent(String title, int duration){
        this.title = title;
        this.duration = duration;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        ConferenceEvent event = (ConferenceEvent) o;
        return -(this.duration - event.getDuration());
    }

}