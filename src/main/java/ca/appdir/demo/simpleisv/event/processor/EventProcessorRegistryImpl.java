package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class EventProcessorRegistryImpl implements EventProcessorRegistry {

    @Autowired
    private NoOpAppDirectEventProcessor noOpAppDirectEventProcessor;

    @Autowired
    private List<AbstractEventProcessor> eventProcessors;

    private Map<EventType, AppDirectEventProcessor> processorsPerType;

    @PostConstruct
    public void init() {
        processorsPerType = eventProcessors.stream()
                                           .collect(Collectors.toMap(AbstractEventProcessor::getEventType, Function.identity()));
    }

    @Override
    public AppDirectEventProcessor getProcessorForEventType(EventType eventType) {
        return Optional.ofNullable(processorsPerType.get(eventType))
                       .orElse(noOpAppDirectEventProcessor);
    }
}
