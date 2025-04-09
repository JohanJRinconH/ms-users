package com.johanrincon.ms.users.controllers;

import com.johanrincon.ms.users.dtos.UsuarioRequest;
import com.johanrincon.ms.users.dtos.UsuarioResponse;
import com.johanrincon.ms.users.exceptions.UsuarioException;
import com.johanrincon.ms.users.services.UsuariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.johanrincon.ms.users.enums.InternalCodes.OPERATION_FAILED;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Controlador para peticiones de usuarios")
@Slf4j
@Transactional
public class UsuariosController {

    private final UsuariosService service;

    /**
     * @param body {@link UsuarioRequest}
     * @return {@link ResponseEntity<UsuarioRequest>}
     */
    @Operation(summary = "Endpoint para registrar usuarios")
    @ApiResponse(responseCode = "201", description = "Usuario registrado en caso de exito")
    @ApiResponse(responseCode = "400", description = "Mensaje de error funcional")
    @ApiResponse(responseCode = "500", description = "Mensaje de error de sistema")
    @PostMapping(path = "/registrar",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponse> registroUsuario(@Valid @RequestBody UsuarioRequest body)
            throws UsuarioException {
        log.info("registroUsuario -- INIT");
        try {
            return service.registrarUsuario(body);
        } catch (Exception e) {
            throw new UsuarioException("ERROR : "+e.getMessage(), OPERATION_FAILED);
        } finally {
            log.info("registroUsuario -- END");
        }
    }
}
