package com.journal.journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/healthcheck") // This defines a relative path.

    public String healthCheck() {
        return "OK";
    }
}
