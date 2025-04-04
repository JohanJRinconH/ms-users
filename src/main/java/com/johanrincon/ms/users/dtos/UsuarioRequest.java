package com.johanrincon.ms.users.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    private UUID id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "El correo debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*/()!]).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, incluyendo letras minúsculas, mayúsculas, números y caracteres especiales")
    private String password;

    private Date createdAt;
    private Date modifiedAt;
    private Date ultimoLogin;
    private String token;
    private boolean isActive;

    @Valid
    @NotNull(message = "La lista de teléfonos no puede estar vacía")
    @NotEmpty(message = "La lista de teléfonos no puede estar vacía")
    private List<TelefonoRequest> phones;
}
