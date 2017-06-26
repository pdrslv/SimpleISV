package ca.appdir.demo.simpleisv.event;

import ca.appdir.demo.simpleisv.event.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class EventRestController {

    @Autowired
    private AppDirectEventBroker appDirectEventBroker;

    @GetMapping(value = "/integration/processEvent", produces = APPLICATION_JSON_VALUE)
    public Response processRequest(@NotBlank @RequestParam("eventUrl") String eventUrl) {
        return appDirectEventBroker.processEvent(eventUrl);
    }
}
