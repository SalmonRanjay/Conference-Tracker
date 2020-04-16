package com.ranjay.mediators;

public class TrackMediator implements ITrackMediator {

    private Boolean isTrackFull = false;

    @Override
    public void setTrackFull(Boolean status) {
        this.isTrackFull = status;

    }

    @Override
    public Boolean isTrackFull() {
        return this.isTrackFull;
    }

}