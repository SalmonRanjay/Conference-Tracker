package com.ranjay;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.ranjay.models.ConferenceEvent;
import com.ranjay.service.ConferenceInitializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConferenceInitializerTest {

    private ConferenceInitializer initializer;

    @BeforeEach
    private void init(){
        initializer = new ConferenceInitializer();
    }

    @Test
    public void initializeEVentsTest(){
        List<ConferenceEvent> events = initializer.initializeEVents("input.txt");
        assertNotNull(events);
        assertTrue(events.size() > 0);
    }
}