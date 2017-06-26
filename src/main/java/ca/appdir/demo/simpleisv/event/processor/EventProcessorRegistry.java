package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event;

public interface EventProcessorRegistry {
    AppDirectEventProcessor getProcessorForEventType(Event.EventType eventType);
}
