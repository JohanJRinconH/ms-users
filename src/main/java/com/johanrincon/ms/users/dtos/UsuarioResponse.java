package com.johanrincon.ms.users.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponse (
        UUID id,
        String name,
        Date created,
        Date modified,
        Date last_login,
        String token,
        Boolean isactive){}