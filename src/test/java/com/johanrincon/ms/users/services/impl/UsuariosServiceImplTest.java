package com.johanrincon.ms.users.services.impl;

import com.johanrincon.ms.users.dtos.TelefonoRequest;
import com.johanrincon.ms.users.dtos.UsuarioRequest;
import com.johanrincon.ms.users.dtos.UsuarioResponse;
import com.johanrincon.ms.users.entities.Usuarios;
import com.johanrincon.ms.users.exceptions.UsuarioException;
import com.johanrincon.ms.users.repositories.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuariosServiceImplTest {

    @InjectMocks
    private UsuariosServiceImpl service;

    @Mock
    private UsuariosRepository usuariosRepository;

    @BeforeEach
    void setUp() {
        service = new UsuariosServiceImpl(usuariosRepository);
        ReflectionTestUtils.setField(service, "JWT_SECRET_KEY", "tBOVh+GMHxhLqBpbmfOjbUdq1wAtsHhJbvObGNUFHS71YRvPNnDnxjGH5uheeR0pMZJQvXZyPNuvHGxChcOZKg==");
    }

    @Test
    void registrarUsuario_Success() {
        UsuarioRequest request = new UsuarioRequest();
        request.setName("John Doe");
        request.setEmail("mail@web.com");
        request.setPassword("12345678s/A");
        request.setPhones(List.of(new TelefonoRequest("12341234", "9", "56")));

        when(usuariosRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        ResponseEntity<UsuarioResponse> response = service.registrarUsuario(request);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("JOHN DOE", response.getBody().name());
    }

    @Test
    void registrarUsuario_EmailAlreadyExists() {
        UsuarioRequest request = new UsuarioRequest();
        request.setName("John Doe");
        request.setEmail("existing@mail.com");
        request.setPassword("12345678s/A");
        request.setPhones(List.of(new TelefonoRequest("12341234", "9", "56")));

        when(usuariosRepository.findByEmail(anyString())).thenReturn(Optional.of(new Usuarios()));

        assertThrows(UsuarioException.class, () -> service.registrarUsuario(request));
    }

    @Test
    void registrarUsuario_ErrorSavingToDB() {
        UsuarioRequest request = new UsuarioRequest();
        request.setName("John Doe");
        request.setEmail("mail@web.com");
        request.setPassword("12345678s/A");
        request.setPhones(List.of(new TelefonoRequest("12341234", "9", "56")));

        when(usuariosRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        doThrow(new RuntimeException()).when(usuariosRepository).save(any(Usuarios.class));

        assertThrows(UsuarioException.class, () -> service.registrarUsuario(request));
    }
}