package com.ranjay.mediators;

public class SessionMediator implements ISessionMediator {
    private Boolean success;

    @Override
    public void setEventSuccessStatus(Boolean status) {
        this.success = status;
    }

    @Override
    public boolean eventAddedSuccessfylly() {
        return this.success;
    }

}