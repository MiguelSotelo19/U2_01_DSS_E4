package mx.edu.utez.almacen.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.product.ProductBean;

import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false)
    private String nombre;
    @Column(length = 8, nullable = false)
    private String codigo;
    @Column(length = 450)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<ProductBean> products;
}
