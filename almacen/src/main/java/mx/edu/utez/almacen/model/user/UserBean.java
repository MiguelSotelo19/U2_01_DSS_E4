package mx.edu.utez.almacen.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.rol.RolBean;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 200, nullable = false)
    private String password;
    @Column(columnDefinition = "TEXT")
    private String avatar;
    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;
    @Column(columnDefinition = "BOOL DEFAULT false")
    private Boolean blocked;
    private String token;

    public UserBean(String username, String password, PersonBean person) {
        this.username = username;
        this.password = password;
        this.personBean = person;
        this.status = true;
        this.createdAt = LocalDateTime.now();
        this.blocked = true;
    }

    @ManyToMany(mappedBy = "users", cascade = CascadeType.MERGE)
    private Set<RolBean> roles;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private PersonBean personBean;


}
