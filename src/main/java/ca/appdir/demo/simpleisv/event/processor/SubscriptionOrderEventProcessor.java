package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event;
import ca.appdir.demo.simpleisv.event.model.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static ca.appdir.demo.simpleisv.event.model.Event.EventType.SUBSCRIPTION_ORDER;

@Slf4j
public class SubscriptionOrderEventProcessor extends AbstractEventProcessor {

    public SubscriptionOrderEventProcessor() {
        super(SUBSCRIPTION_ORDER);
    }

    @Override
    public Response process(Event event) {
        log.info("Processing [{}] event {}", getEventType(), event);
        String accountIdentifier = UUID.randomUUID()
                                       .toString();
        log.info("The following account identifier has been generated [{}]", accountIdentifier);
        return Response.builder()
                       .message("Subscription successfully processed")
                       .success(true)
                       .accountIdentifier(accountIdentifier)
                       .build();
    }
}
