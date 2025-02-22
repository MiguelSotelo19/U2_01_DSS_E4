package mx.edu.utez.almacen.model.user;

import mx.edu.utez.almacen.model.category.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {
    Optional<UserBean> findFirstByUsername(String username);

    /* Method queries
     * 1. Buscar a todos los usuarios por medio del status
     * 2. Buscar a un usuario por medio del ID de la persona
     * 3. Buscar al usuario por medio del curp de la persona
     * */
    List<UserBean> findAllByStatus(Boolean status);

    Optional<UserBean> findByPersonBeanId(Long id);

    Optional<UserBean> findByPersonBeanCurp(String curp);

    @Query(value = "SELECT * FROM user u " +
            "INNER JOIN person p ON u.person_id = p.id " +
            "WHERE p.birthdate BETWEEN :fechaUno AND :fechaDos ",
            nativeQuery = true)
    List<UserBean> getUsers(@Param("fechaUno") String startDate,
                        @Param("fechaDos") String endDate);

    @Modifying
    @Query(value = "INSERT INTO user_roles(user_id, role_id) " +
            "VALUES ( :userId, :roleId )", nativeQuery = true)
    int saveUserRole(Long userId, Long roleId);
    @Query(value = "SELECT user_id FROM user_roles WHERE user_id = :userId AND " +
            "role_id = :roleId ", nativeQuery = true)
    Long getIdUserRoles(Long userId, Long roleId);
}
