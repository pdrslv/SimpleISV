package ca.appdir.demo.simpleisv.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.token.InMemorySelfCleaningProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OAuthConfiguration {

    @Value("${appdirect.product.integration.oauth.consumer.key}")
    private String consumerKey;

    @Value("${appdirect.product.integration.oauth.consumer.secret}")
    private String consumerSecret;

    @Bean
    public ConsumerDetails consumerDetails() {
        BaseConsumerDetails baseConsumerDetails = new BaseConsumerDetails();
        baseConsumerDetails.setConsumerKey(consumerKey);
        baseConsumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(consumerSecret));
        baseConsumerDetails.setRequiredToObtainAuthenticatedToken(false);
        return baseConsumerDetails;
    }

    @Bean
    public ConsumerDetailsService consumerDetailsService(@Autowired ConsumerDetails consumerDetails) {
        Map<String, ConsumerDetails> consumerDetailsStore = new HashMap<>();
        consumerDetailsStore.put(consumerDetails.getConsumerKey(), consumerDetails);
        InMemoryConsumerDetailsService inMemoryConsumerDetailsService = new InMemoryConsumerDetailsService();
        inMemoryConsumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);
        return inMemoryConsumerDetailsService;
    }

    @Bean
    public OAuthProviderTokenServices oAuthProviderTokenServices() {
        return new InMemorySelfCleaningProviderTokenServices();
    }

    @Bean
    public OAuthRestTemplate oAuthRestTemplate(@Autowired ConsumerDetails consumerDetails) {
        BaseProtectedResourceDetails baseProtectedResourceDetails = new BaseProtectedResourceDetails();
        baseProtectedResourceDetails.setConsumerKey(consumerDetails.getConsumerKey());
        baseProtectedResourceDetails.setSharedSecret(consumerDetails.getSignatureSecret());

        return new OAuthRestTemplate(baseProtectedResourceDetails);
    }

}
