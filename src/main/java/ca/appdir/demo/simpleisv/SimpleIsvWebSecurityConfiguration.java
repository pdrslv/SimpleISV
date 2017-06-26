package ca.appdir.demo.simpleisv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SimpleIsvWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ConsumerDetailsService consumerDetailsService;

    @Autowired
    private OAuthProviderTokenServices oAuthProviderTokenServices;


    private ProtectedResourceProcessingFilter protectedResourceProcessingFilter() {
        ProtectedResourceProcessingFilter protectedResourceProcessingFilter = new ProtectedResourceProcessingFilter();
        protectedResourceProcessingFilter.setConsumerDetailsService(consumerDetailsService);
        protectedResourceProcessingFilter.setTokenServices(oAuthProviderTokenServices);
        protectedResourceProcessingFilter.setIgnoreMissingCredentials(false);
        return protectedResourceProcessingFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .antMatcher("/integration/**")
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .addFilterBefore(protectedResourceProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}
