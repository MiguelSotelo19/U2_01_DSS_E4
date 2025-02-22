package com.example.PracticaCocker.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuariosBean, Long> {
    Optional<UsuariosBean> findByCorreo(String correo);
}
