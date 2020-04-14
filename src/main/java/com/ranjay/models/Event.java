package com.ranjay.models;

import lombok.Data;

@Data
public class Event implements Comparable {

    private String title;
    private int duration;

    public Event(String title, int duration){
        this.title = title;
        this.duration = duration;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        Event event = (Event) o;
        return -(this.duration - event.getDuration());
    }

}