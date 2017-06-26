package ca.appdir.demo.simpleisv.event;

import ca.appdir.demo.simpleisv.event.model.Event;

public interface AppDirectRestClient {
    Event retrieveEvent(String eventUrl);
}
