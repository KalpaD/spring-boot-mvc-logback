package com.kds.service;

import com.kds.modle.TitleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetService {

    private Logger LOGGER = LoggerFactory.getLogger(GreetService.class);

    @Autowired
    private RestTemplate restTemplate;

    public String getGreet(String name) {

        LOGGER.info("Invoking {} + getTitle()", GreetService.class );

        ResponseEntity<TitleResponse> response = restTemplate.getForEntity("http://localhost:1080/title", TitleResponse.class);

        return "Hello " + response.getBody().getTitle() + " " + name + "!";
    }

}
