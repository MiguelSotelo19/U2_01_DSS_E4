package mx.edu.utez.almacen.model.ticket;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketBean, Long> {
}
