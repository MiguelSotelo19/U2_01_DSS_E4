package com.example.Practica1.controller.Usuario;


import com.example.Practica1.Model.Usuario.UsuariosBean;
import com.example.Practica1.controller.Usuario.DTO.UsuarioDto;
import com.example.Practica1.Service.Usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public UsuariosBean registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuariosBean usuario = new UsuariosBean(usuarioDto.getNombre(), usuarioDto.getApellidoPaterno(), usuarioDto.getApellidoPaterno(), usuarioDto.getCorreo(), usuarioDto.getNumeroTelefonico(), usuarioDto.getContrasena(),usuarioDto.getEdad());
        return usuarioService.registrarUsuario(usuario);
    }

    @PostMapping("/login")
    public Optional<UsuariosBean> login(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.login(usuarioDto.getCorreo());
    }
}
