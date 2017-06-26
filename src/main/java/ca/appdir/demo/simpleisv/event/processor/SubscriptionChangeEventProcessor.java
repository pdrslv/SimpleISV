package ca.appdir.demo.simpleisv.event.processor;

import ca.appdir.demo.simpleisv.event.model.Event;
import ca.appdir.demo.simpleisv.event.model.Event.Order;
import ca.appdir.demo.simpleisv.event.model.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static ca.appdir.demo.simpleisv.event.model.Event.EventType.SUBSCRIPTION_CHANGE;

@Slf4j
public class SubscriptionChangeEventProcessor extends AbstractEventProcessor {

    public SubscriptionChangeEventProcessor() {
        super(SUBSCRIPTION_CHANGE);
    }

    @Override
    public Response process(Event event) {
        log.info("Processing [{}] event {}", getEventType(), event);
        Optional.ofNullable(event)
                .map(Event::getPayload)
                .ifPresent(payload -> {
                    String accountIdentifier = payload.getAccount()
                                                      .getAccountIdentifier();

                    Order order = payload.getOrder();
                    log.info("Will process the following order [{}] for account [{}]", order, accountIdentifier);
                });
        return Response.builder()
                       .message("Subscription Change successfully processed")
                       .success(true)
                       .build();
    }
}
