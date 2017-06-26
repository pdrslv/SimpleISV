package ca.appdir.demo.simpleisv.event.processor;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class EventProcessorConfiguration {

    @Bean
    public SubscriptionCancelEventProcessor subscriptionCancelEventProcessor() {
        return new SubscriptionCancelEventProcessor();
    }

    @Bean
    public SubscriptionChangeEventProcessor subscriptionChangeEventProcessor() {
        return new SubscriptionChangeEventProcessor();
    }

    @Bean
    public SubscriptionOrderEventProcessor subscriptionOrderEventProcessor() {
        return new SubscriptionOrderEventProcessor();
    }

    @Bean
    public NoOpAppDirectEventProcessor noOpAppDirectEventProcessor() {
        return new NoOpAppDirectEventProcessor();
    }

    @Bean
    public EventProcessorRegistry eventProcessorRegistry() {
        return new EventProcessorRegistryImpl();
    }
}
