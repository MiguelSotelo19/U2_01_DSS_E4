package com.example.PracticaCocker.Service.usuario;

import com.example.PracticaCocker.model.usuario.UsuariosBean;
import com.example.PracticaCocker.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository UsuarioRepository;

    public UsuariosBean registrarUsuario(UsuariosBean usuario) {
        return UsuarioRepository.save(usuario);
    }

    public Optional<UsuariosBean> login(String correo) {
        return UsuarioRepository.findByCorreo(correo);
    }

    public List<UsuariosBean> obtenerTodosLosUsuarios() {
        return UsuarioRepository.findAll();
    }
}

