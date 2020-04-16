package com.ranjay.models;

import lombok.Data;

@Data
public class Event implements Comparable<Event> {

    private String title;
    private int duration;

    public Event(String title, int duration){
        this.title = title;
        this.duration = duration;
    }

    @Override
    public int compareTo(Event event) {
        return -(this.duration - event.getDuration());
    }

}