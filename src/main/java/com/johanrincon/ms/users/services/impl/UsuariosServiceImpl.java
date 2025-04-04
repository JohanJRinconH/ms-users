package com.johanrincon.ms.users.services.impl;

import com.johanrincon.ms.users.dtos.UsuarioRequest;
import com.johanrincon.ms.users.dtos.UsuarioResponse;
import com.johanrincon.ms.users.entities.Telefonos;
import com.johanrincon.ms.users.entities.Usuarios;
import com.johanrincon.ms.users.exceptions.UsuarioException;
import com.johanrincon.ms.users.repositories.UsuariosRepository;
import com.johanrincon.ms.users.services.UsuariosService;
import com.johanrincon.ms.users.utils.DateUtils;
import com.johanrincon.ms.users.utils.JWTUtils;
import com.johanrincon.ms.users.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.johanrincon.ms.users.enums.InternalCodes.INTERNAL_SERVER_ERROR;
import static com.johanrincon.ms.users.enums.InternalCodes.OPERATION_FAILED;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuariosServiceImpl implements UsuariosService {

    @Value("${jwt.secret.key}")
    private String JWT_SECRET_KEY;

    private final UsuariosRepository usuariosRepository;

    @Override
    public ResponseEntity<UsuarioResponse> registrarUsuario(UsuarioRequest body) throws UsuarioException {
        log.info("Registrando usuario: {}", body.getEmail());

        Optional<Usuarios> usuario = usuariosRepository.findByEmail(body.getEmail());
        if (usuario.isPresent()) {
            throw new UsuarioException("El correo ya registrado", OPERATION_FAILED);
        };

        Date date = DateUtils.getDate();

        Usuarios usuarioNuevo =  Usuarios.builder()
                .created(date)
                .modified(date)
                .lastLogin(date)
                .email(body.getEmail())
                .nombre(body.getName().toUpperCase(Locale.getDefault()))
                .password(SecurityUtils.encryptPassword(body.getPassword()))
                .token(JWTUtils.generateJWT(body.getEmail(), JWT_SECRET_KEY))
                .isActive(true)
                .build();

        boolean passwordChecks = SecurityUtils.checkPassword(body.getPassword(), usuarioNuevo.getPassword());
        log.info("Password checks: {}", passwordChecks);

        List<Telefonos> listTelefono = body.getPhones().stream()
                .map(telefono -> Telefonos.builder()
                        .number(telefono.getNumber())
                        .citycode(telefono.getCitycode())
                        .countrycode(telefono.getCountrycode())
                        .usuario(usuarioNuevo)
                        .build())
                .collect(Collectors.toList());

        usuarioNuevo.setTelefonos(listTelefono);

        try {
            usuariosRepository.save(usuarioNuevo);
        } catch (Exception e) {
            throw new UsuarioException("ERROR captado al guardar en la BD", INTERNAL_SERVER_ERROR);
        }

        UsuarioResponse response = new UsuarioResponse(
                usuarioNuevo.getId(),
                usuarioNuevo.getNombre(),
                usuarioNuevo.getCreated(),
                usuarioNuevo.getModified(),
                usuarioNuevo.getLastLogin(),
                usuarioNuevo.getToken(),
                usuarioNuevo.isActive()
        );

        log.info("Registrado usuario: {}", response.id());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
