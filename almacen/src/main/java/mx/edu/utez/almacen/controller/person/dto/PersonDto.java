package mx.edu.utez.almacen.controller.person.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.almacen.model.person.PersonBean;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PersonDto {
    private Long id;
    private String names;
    private String surname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private String address;
    private String curp;

    public PersonBean toEntity(){
        return new PersonBean(names, surname, lastname, birthdate, email, phoneNumber, address, curp);
    }
    public PersonBean toEntityId(){
        return new PersonBean(id, names, surname, lastname, birthdate, email, phoneNumber, address, curp);
    }

}
