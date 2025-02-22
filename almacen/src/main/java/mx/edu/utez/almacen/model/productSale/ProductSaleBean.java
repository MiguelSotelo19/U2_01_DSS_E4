package mx.edu.utez.almacen.model.productSale;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.ticket.TicketBean;

@Entity
@Table(name = "product_sale")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductSaleBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryBean inventoryBean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketBean ticketBean;
}
