package com.development.secure.software.eventplanner.configuration;

import com.development.secure.software.eventplanner.service.CalendarService;
import com.development.secure.software.eventplanner.service.CalendarServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 4:56 PM
 * @Version 1.0
 * Spring OAuth2 Security Configurations
 */
@Configuration
public class SpringSecOauthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login.html", "/webjars/**")
                .permitAll().anyRequest().authenticated().and().oauth2Login().loginPage("/login.html")
                .defaultSuccessUrl("/api/google/grant", true).failureUrl("/login.html");
    }

    @Bean
    @RequestScope
    public CalendarService calendarService(OAuth2AuthorizedClientService clientService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
            if (clientRegistrationId.equals("google")) {
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
                accessToken = client.getAccessToken().getTokenValue();
            }
        }
        return new CalendarServiceImpl(accessToken);
    }


}
