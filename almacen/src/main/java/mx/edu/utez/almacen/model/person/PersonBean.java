package mx.edu.utez.almacen.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.user.UserBean;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String names;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(length = 50, nullable = false)
    private String lastname;
    @Column
    private int age;
    @Column(length = 150, nullable = true)
    private String email;
    @Column(length = 10, nullable = true)
    private String phoneNumber;
    @Column(length = 18, nullable = true)
    private String curp;


    @JsonIgnore
    @OneToOne(mappedBy = "personBean")
    private UserBean userBean;


    public PersonBean(Long id,String names, String surname, String lastname, int age, String email, String phoneNumber, String curp) {
        this.id = id;
        this.names = names;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.curp = curp;
    }

    public PersonBean(String names, String surname, String lastname, int age, String email, String phoneNumber, String curp) {
        this.names = names;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.curp = curp;
    }


    public PersonBean(String name, String surname, String lastname, int age, String curp, String email) {
        this.names = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.curp = curp;
    }

}
