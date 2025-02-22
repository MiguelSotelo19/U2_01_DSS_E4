package mx.edu.utez.almacen.model.productSale;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducSaleRepository extends JpaRepository<ProductSaleBean, Long> {
}
