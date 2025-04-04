package com.johanrincon.ms.users.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest {

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}$",
            message = "El número de teléfono debe tener 8 números")
    private String number;

    @NotBlank(message = "El código de ciudad no puede estar vacío")
    @Pattern(regexp = "^[0-9]{1}$",
            message = "El codigo de ciudad debe tener 1 número")
    private String citycode;

    @NotBlank(message = "El código de país no puede estar vacío")
    @Pattern(regexp = "^[0-9]{2}$",
            message = "El codigo de pais debe tener 2 números")
    private String countrycode;

}
