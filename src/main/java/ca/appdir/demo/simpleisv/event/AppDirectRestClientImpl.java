package ca.appdir.demo.simpleisv.event;

import ca.appdir.demo.simpleisv.SimpleIsvException;
import ca.appdir.demo.simpleisv.event.model.Event;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

import java.net.URI;
import java.util.function.Predicate;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
public class AppDirectRestClientImpl implements AppDirectRestClient {

    private Predicate<ResponseEntity> isSuccessFullResponse = response -> response.getStatusCode()
                                                                                  .is2xxSuccessful();

    @Autowired
    private OAuthRestTemplate oAuthRestTemplate;

    @Override
    public Event retrieveEvent(String eventUrl) {
        Event event = makeRequest(eventUrl);
        log.info("Successfully retrieved event {}", event);
        return event;
    }

    private Event makeRequest(String eventUrl) {
        RequestEntity requestEntity = createRequestEntity(eventUrl);
        return Try.of(() -> oAuthRestTemplate.exchange(requestEntity, Event.class))
                  .filter(isSuccessFullResponse)
                  .map(ResponseEntity::getBody)
                  .getOrElseThrow(t -> {
                      log.error("AppDirect responded with Error [{}]", t.getMessage());
                      return new SimpleIsvException("AppDirect connection error", t);
                  });

    }

    private RequestEntity<?> createRequestEntity(String eventUrl) {
        return RequestEntity.get(URI.create(eventUrl))
                            .accept(APPLICATION_JSON)
                            .build();
    }

}
