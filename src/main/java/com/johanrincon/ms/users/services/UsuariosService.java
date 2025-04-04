package com.johanrincon.ms.users.services;

import com.johanrincon.ms.users.dtos.UsuarioRequest;
import com.johanrincon.ms.users.dtos.UsuarioResponse;
import com.johanrincon.ms.users.exceptions.UsuarioException;
import org.springframework.http.ResponseEntity;

public interface UsuariosService {

    ResponseEntity<UsuarioResponse> registrarUsuario(UsuarioRequest body) throws UsuarioException;

}
