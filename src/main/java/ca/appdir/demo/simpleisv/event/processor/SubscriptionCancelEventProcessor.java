package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event;
import ca.appdir.demo.simpleisv.event.model.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static ca.appdir.demo.simpleisv.event.model.Event.EventType.SUBSCRIPTION_CANCEL;

@Slf4j
public class SubscriptionCancelEventProcessor extends AbstractEventProcessor {

    public SubscriptionCancelEventProcessor() {
        super(SUBSCRIPTION_CANCEL);
    }

    @Override
    public Response process(Event event) {
        log.info("Processing [{}] event {}", getEventType(), event);
        Optional.ofNullable(event)
                .map(Event::getPayload)
                .map(Event.Payload::getAccount)
                .ifPresent(account -> {
                    log.info("Will cancel subscription for account [{}]", account.getAccountIdentifier());
                });

        return Response.builder()
                       .message("Subscription Cancellation successfully processed")
                       .success(true)
                       .build();
    }
}
