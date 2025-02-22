package mx.edu.utez.almacen.model.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.ticket.TicketBean;

import java.util.Set;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;
    @Column(name = "address", length = 150, nullable = false)
    private String address;
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @OneToMany(mappedBy = "clientBeans", cascade = CascadeType.ALL)
    private Set<TicketBean> ticketBeans;
}
