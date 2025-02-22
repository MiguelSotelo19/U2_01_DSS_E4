package mx.edu.utez.almacen.model.person;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonBean, Long> {
Optional<PersonBean> findByCurp(String curp);
}
