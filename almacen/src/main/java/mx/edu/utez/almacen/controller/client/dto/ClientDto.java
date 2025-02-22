package mx.edu.utez.almacen.controller.client.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.ticket.TicketBean;

import java.util.Set;

public class ClientDto {
    private Long id;
    private String companyName;
    private String address;
    private String phoneNumber;
    private String email;
    private Set<TicketBean> ticketBeans;
}
