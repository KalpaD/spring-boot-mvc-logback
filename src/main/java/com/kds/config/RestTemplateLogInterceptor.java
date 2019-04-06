package com.kds.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateLogInterceptor implements ClientHttpRequestInterceptor {

    private Logger LOGGER = LoggerFactory.getLogger(RestTemplateLogInterceptor.class);


    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        LOGGER.info("Making a HTTP request to downstream application with HTTP method {} and URI {}",
                httpRequest.getMethod(),
                httpRequest.getURI());

        ClientHttpResponse response = execution.execute(httpRequest, body);

        LOGGER.info("Response received from downstream application with response code {}", response.getStatusCode());

        return response;
    }
}
