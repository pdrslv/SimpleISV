package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event;
import ca.appdir.demo.simpleisv.event.model.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoOpAppDirectEventProcessor implements AppDirectEventProcessor {

    @Override
    public Response process(Event event) {
        log.info("No-OP Processing for Event {}", event);

        return Response.builder()
                       .message("Event successfully processed")
                       .success(true)
                       .build();
    }

}
