package mx.edu.utez.almacen.controller.person.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.almacen.model.person.PersonBean;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PersonDto {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String names;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    @Size(max = 50, message = "El apellido paterno no debe exceder los 50 caracteres")
    private String surname;

    @NotBlank(message = "El apellido materno no puede estar vacío")
    @Size(max = 50, message = "El apellido materno no debe exceder los 50 caracteres")
    private String lastname;

    @NotBlank(message = "La edad no puede estar vacia")
    @Size(max = 100, message = "La edad no puede estar vacia")
    private int age;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    @Pattern(regexp = "^[a-z0-9._%+-]+@gmail\\.com$", message = "El correo debe ser de Gmail y en minúsculas")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe contener exactamente 10 dígitos")
    private String phoneNumber;

    private String curp;

    public PersonBean toEntity(){
        return new PersonBean(names, surname, lastname, age, email, phoneNumber, curp);
    }
    public PersonBean toEntityId(){
        return new PersonBean(id, names, surname, lastname, age, email, phoneNumber, curp);
    }



}
