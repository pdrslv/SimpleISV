package ca.appdir.demo.simpleisv.event;

import ca.appdir.demo.simpleisv.event.model.Response;

public interface AppDirectEventBroker {
    Response processEvent(String eventUrl);
}
