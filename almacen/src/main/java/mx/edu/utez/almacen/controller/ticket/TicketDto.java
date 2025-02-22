package mx.edu.utez.almacen.controller.ticket;

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
public class TicketDto {
    private Long id;
    private LocalTime saleHour;
    private LocalDate saleDate;
    private Double totPay;
    private String folio;
    private Integer totProd;
    private ClientBean clientBeans;

    private Set<ProductSaleBean> productSaleBeans;

    private UserBean userBean;
}
