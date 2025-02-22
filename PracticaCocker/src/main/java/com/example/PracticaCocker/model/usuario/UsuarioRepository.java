package com.example.PracticaCocker.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuariosBean, Long> {
    Optional<UsuariosBean> findByCorreo(String correo);
}
