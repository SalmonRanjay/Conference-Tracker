package com.ranjay.models;

import java.util.List;

import lombok.Data;

@Data
public class ConferenceSchedule {
    private List<Track> conferenceTracks;
}