package com.development.secure.software.eventplanner.interceptor;

import com.development.secure.software.eventplanner.util.ExceptionMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;



/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 9:11 PM
 * @Version 1.0
 */
public abstract class RequestInterceptor {

    protected RestTemplate restTemplate;
    protected String accessToken;

    public RequestInterceptor(String accessToken) {
        this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.accessToken = accessToken;
            this.restTemplate.getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        } else {
            this.restTemplate.getInterceptors().add(getNoTokenInterceptor());
        }
    }

    private ClientHttpRequestInterceptor
    getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor =
                new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes,
                                                        ClientHttpRequestExecution execution) throws IOException {
                        request.getHeaders().add("Authorization", "Bearer " + accessToken);
                        return execution.execute(request, bytes);
                    }
                };
        return interceptor;
    }

    private ClientHttpRequestInterceptor getNoTokenInterceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] bytes,
                                                ClientHttpRequestExecution execution) throws IOException {
                throw new IllegalStateException(ExceptionMessage.ACCESS_TOKEN_NOT_FOUND);
            }
        };
    }

}
