package com.johanrincon.ms.users.repositories;

import com.johanrincon.ms.users.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {

    Optional<Usuarios> findByEmail(String email);

}
