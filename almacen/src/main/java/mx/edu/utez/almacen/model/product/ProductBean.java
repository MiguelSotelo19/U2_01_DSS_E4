package mx.edu.utez.almacen.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.rol.RolBean;

import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String trademark;
    @Column(length = 100, nullable = true)
    private String model;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Boolean status;

    public ProductBean(Long id, String name, String trademark, String model, Double price, Boolean status, CategoryBean category) {
        this.id = id;
        this.name = name;
        this.trademark = trademark;
        this.model = model;
        this.price = price;
        this.status = status;
        this.category = category;
    }

    public ProductBean(String name, String trademark, String model, Double price, Boolean status) {
        this.name = name;
        this.trademark = trademark;
        this.model = model;
        this.price = price;
        this.status = status;
    }

    public ProductBean(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryBean category;

    @OneToOne(mappedBy = "productBean")
    private InventoryBean inventoryBeans;

}
