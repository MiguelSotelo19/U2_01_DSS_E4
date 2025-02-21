package com.example.Practica1.Service.Usuario;

import com.example.Practica1.Model.Usuario.UsuariosBean;
import com.example.Practica1.Model.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
