package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event;
import ca.appdir.demo.simpleisv.event.model.Response;

public interface AppDirectEventProcessor {

    Response process(Event event);

}
