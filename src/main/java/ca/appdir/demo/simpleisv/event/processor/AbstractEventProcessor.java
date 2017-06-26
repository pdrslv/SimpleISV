package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event.EventType;
import lombok.Data;

@Data
public abstract class AbstractEventProcessor implements AppDirectEventProcessor {

    private final EventType eventType;

}
