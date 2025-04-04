package com.johanrincon.ms.users.controllers;

import com.johanrincon.ms.users.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "HealthCheck", description = "Controllar de salud del componente")
@RestController
@RequestMapping(path = "/api")
@Slf4j
public class HealthCheckController {

    /**
     *
     * @return ResponseEntity<String>
     */
    @Operation(summary = "HealthCheck con respuesta 204",
    responses = {
            @ApiResponse(responseCode = "204",
            description = "No Content")
    })
    @GetMapping("/healthCheck")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>(HeadersUtils.getGenericHeaders(), HttpStatus.NO_CONTENT);
    }

}
