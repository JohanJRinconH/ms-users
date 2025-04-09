package com.johanrincon.ms.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johanrincon.ms.users.dtos.TelefonoRequest;
import com.johanrincon.ms.users.dtos.UsuarioRequest;
import com.johanrincon.ms.users.enums.InternalCodes;
import com.johanrincon.ms.users.exceptions.UsuarioException;
import com.johanrincon.ms.users.services.UsuariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class UsuariosControllerTest {

  @InjectMocks
  private UsuariosController controller;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Mock
  private UsuariosService service;

  @BeforeEach
  void setUp(WebApplicationContext webApplicationContext) {
    controller = new UsuariosController(service);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void testRegistroUsuario_Success() throws Exception {
    UsuarioRequest request = new UsuarioRequest();
    request.setName("John Doe");
    request.setEmail("mail@web.com");
    request.setPassword("12345678s/A");
    request.setPhones(List.of(new TelefonoRequest(
            "12341234", "9", "56")));

    mockMvc.perform(post("/usuarios/registrar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());
  }

  @Test
  void testRegistroUsuario_EmailAlreadyExists() throws Exception {
    UsuarioRequest request = new UsuarioRequest();
    request.setName("John Doe");
    request.setEmail("existing@mail.com");
    request.setPassword("12345678s/A");
    request.setPhones(List.of(new TelefonoRequest(
            "12341234", "9", "56")));

    doThrow(new UsuarioException("El correo ya registrado", InternalCodes.OPERATION_FAILED))
            .when(service).registrarUsuario(any(UsuarioRequest.class));

    assertThrows(UsuarioException.class, () -> controller.registroUsuario(request));
  }

  @Test
  void testRegistroUsuario_InvalidRequest() throws Exception {
    UsuarioRequest request = new UsuarioRequest();
    request.setName("John Doe");

    mockMvc.perform(post("/usuarios/registrar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is5xxServerError());
  }
}