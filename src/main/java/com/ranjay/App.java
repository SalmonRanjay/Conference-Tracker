package com.ranjay;

import java.util.List;

import com.ranjay.models.ConferenceEvent;
import com.ranjay.service.ConferenceInitializer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ConferenceInitializer initializer = new ConferenceInitializer();
        List<ConferenceEvent> events = initializer.initializeEVents("input.txt");
    }
}
