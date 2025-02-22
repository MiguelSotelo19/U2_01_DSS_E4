package mx.edu.utez.almacen.controller.product.dto;

import jakarta.persistence.*;
import lombok.*;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.product.ProductBean;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String trademark;
    private String model;
    private Double price;
    private boolean status;
    private CategoryBean category;

    public ProductBean toEntity(){
        if (category == null)
            return new ProductBean(name, trademark, model, price, status);
        return new ProductBean(id, name, trademark, model, price, status, category);
    }

}
