package mx.edu.utez.almacen.model.logbean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String action; // Método HTTP: GET, POST, PUT, DELETE

    @Column(nullable = false)
    private String endpoint; // URL del endpoint accedido

    @Column(nullable = false)
    private LocalDateTime timestamp; // Fecha y hora de la acción
}
