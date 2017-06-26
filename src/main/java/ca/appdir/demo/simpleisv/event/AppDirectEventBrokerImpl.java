package ca.appdir.demo.simpleisv.event;

import ca.appdir.demo.simpleisv.event.model.Event;
import ca.appdir.demo.simpleisv.event.model.Response;
import ca.appdir.demo.simpleisv.event.processor.AppDirectEventProcessor;
import ca.appdir.demo.simpleisv.event.processor.EventProcessorRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AppDirectEventBrokerImpl implements AppDirectEventBroker {

    @Autowired
    private AppDirectRestClient appDirectRestClient;

    @Autowired
    private EventProcessorRegistry eventProcessorRegistry;

    @Override
    public Response processEvent(String eventUrl) {
        log.info("Will retrieve event from eventUrl [{}]", eventUrl);
        Event event = appDirectRestClient.retrieveEvent(eventUrl);
        AppDirectEventProcessor eventProcessor = eventProcessorRegistry.getProcessorForEventType(event.getType());
        log.info("Event Processor resolved [{}]", eventProcessor.getClass()
                                                                .getSimpleName());
        return eventProcessor.process(event);
    }

}
