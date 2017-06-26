package ca.appdir.demo.simpleisv.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfiguration {

    @Bean
    public AppDirectRestClient appDirectRestClient() {
        return new AppDirectRestClientImpl();
    }

    @Bean
    public AppDirectEventBroker appDirectEventBroker() {
        return new AppDirectEventBrokerImpl();
    }

}
