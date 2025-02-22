package mx.edu.utez.almacen.controller.inventory.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.product.ProductBean;
import mx.edu.utez.almacen.model.productSale.ProductSaleBean;

import java.time.LocalDate;
import java.util.Set;
public class InventoryDto {
    private Long id;
    private LocalDate expiration;
    private Integer stock;
    private Double price;

    private Long productBean;
    private Set<ProductSaleBean> productSaleBeans;

    public InventoryBean toEntity(){
        return new InventoryBean(id, expiration, stock, price, new ProductBean(productBean));
    }
}
