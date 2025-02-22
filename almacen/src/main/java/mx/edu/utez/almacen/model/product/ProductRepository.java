package mx.edu.utez.almacen.model.product;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductBean, Long> {
}
