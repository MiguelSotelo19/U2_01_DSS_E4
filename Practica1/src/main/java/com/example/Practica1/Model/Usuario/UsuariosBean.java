package com.example.Practica1.Model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellidoPaterno;

    @Column(length = 100, nullable = false)
    private String apellidoMaterno;

    @Column(length = 100, nullable = false)
    private String correo;

    @Column(length = 10, nullable = false)
    private String numeroTelefonico;

    private int edad;

    public UsuariosBean(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String numeroTelefonico, int edad) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.numeroTelefonico = numeroTelefonico;
        this.edad = edad;
    }
}
