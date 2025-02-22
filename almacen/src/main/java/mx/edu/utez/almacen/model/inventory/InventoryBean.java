package mx.edu.utez.almacen.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.product.ProductBean;
import mx.edu.utez.almacen.model.productSale.ProductSaleBean;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InventoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE", nullable = true)
    private LocalDate expiration;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Double price;

    public InventoryBean(Long id, LocalDate expiration, Integer stock, Double price, ProductBean productBean) {
        this.id = id;
        this.expiration = expiration;
        this.stock = stock;
        this.price = price;
        this.productBean = productBean;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductBean productBean;

    @OneToMany(mappedBy = "inventoryBean")
    private Set<ProductSaleBean> productSaleBeans;
}
