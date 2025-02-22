package mx.edu.utez.almacen.controller.productSale.dto;

import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.ticket.TicketBean;

public class ProductSaleDto {
    private Long id;
    private Integer quantity;
    private Double total;

    private InventoryBean inventoryBean;

    private TicketBean ticketBean;
}
