package com.kds.controller;

import com.kds.service.GreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetController {

    private Logger LOGGER = LoggerFactory.getLogger(GreetController.class);

    @Autowired
    private GreetService greetService;

    @GetMapping(value = "/greet")
    public ResponseEntity<String> getGreet(@RequestParam(name = "name") String name) {

        LOGGER.info("Request received for /greet at {} getGreet()", GreetController.class);

        String greet = greetService.getGreet(name);

        LOGGER.trace("Trace - {}", greet);
        LOGGER.debug("Debug - {}", greet);
        LOGGER.info("Info - {}", greet);
        LOGGER.warn("Warn - {}", greet);
        LOGGER.error("Error - {}", greet);

        LOGGER.info("Returning response from  /greet at {} getGreet()", GreetController.class);

        return new ResponseEntity<>(greet, HttpStatus.OK);
    }
}
