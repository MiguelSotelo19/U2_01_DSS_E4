package com.example.Practica1.controller.Usuario.DTO;
import com.example.Practica1.Model.Usuario.UsuariosBean;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String numeroTelefonico;
    private int edad;

    public UsuariosBean toEntity(){
        return new UsuariosBean(nombre, apellidoPaterno, apellidoMaterno, correo, numeroTelefonico, edad);
    }

    public String getNombre() {
        return this.nombre;
    }


}

