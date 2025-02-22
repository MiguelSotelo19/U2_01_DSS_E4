package mx.edu.utez.almacen.model.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.client.ClientBean;
import mx.edu.utez.almacen.model.productSale.ProductSaleBean;
import mx.edu.utez.almacen.model.user.UserBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIME", nullable = false)
    private LocalTime saleHour;
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate saleDate;
    @Column(nullable = false)
    private Double totPay;
    @Column(length = 15, nullable = false)
    private String folio;
    @Column(nullable = false)
    private Integer totProd;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientBean clientBeans;

    @OneToMany(mappedBy = "ticketBean")
    private Set<ProductSaleBean> productSaleBeans;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserBean userBean;
}
