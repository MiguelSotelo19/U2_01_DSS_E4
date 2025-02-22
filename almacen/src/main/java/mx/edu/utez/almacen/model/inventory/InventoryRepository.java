package mx.edu.utez.almacen.model.inventory;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryBean, Long> {
}
