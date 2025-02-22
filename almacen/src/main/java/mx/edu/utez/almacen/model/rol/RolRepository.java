package mx.edu.utez.almacen.model.rol;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolBean, Long> {
    Optional<RolBean> findByRol(String rol);

    @Modifying
    @Query(value = "INSERT INTO user_role (role_id, user_id) VALUES (:roleId, :userId)",
    nativeQuery = true)
    int saveUserRole(@Param("roleId")Long roleId, @Param("userId") Long userId);
}
