package com.johanrincon.ms.users.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class HealthCheckControllerTest {

    @InjectMocks
    HealthCheckController controller;


    @Test
    void healthCheck() {

        ResponseEntity<String> response = controller.healthCheck();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }
}