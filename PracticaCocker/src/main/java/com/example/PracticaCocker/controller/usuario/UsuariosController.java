package com.example.PracticaCocker.controller.usuario;

import com.example.PracticaCocker.Service.usuario.UsuarioService;
import com.example.PracticaCocker.controller.usuario.dto.UsuariosDTO;
import com.example.PracticaCocker.model.usuario.UsuariosBean;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class UsuariosController  {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public UsuariosBean registrarUsuario(@RequestBody UsuariosDTO usuarioDto) {
        UsuariosBean usuario = new UsuariosBean(usuarioDto.getNombre(), usuarioDto.getApellidoPaterno(), usuarioDto.getApellidoPaterno(), usuarioDto.getCorreo(), usuarioDto.getNumeroTelefonico(), usuarioDto.getContrasena(),usuarioDto.getEdad());
        return usuarioService.registrarUsuario(usuario);
    }

    @PostMapping("/login")
    public Optional<UsuariosBean> login(@RequestBody UsuariosDTO usuarioDto) {
        return usuarioService.login(usuarioDto.getCorreo());
    }

    @GetMapping("/listar")
    public List<UsuariosBean> obtenerUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

}
