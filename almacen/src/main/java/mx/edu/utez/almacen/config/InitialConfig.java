package mx.edu.utez.almacen.config;

import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.person.PersonRepository;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.rol.RolRepository;
import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class InitialConfig implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder encoder;

    public InitialConfig(RolRepository rolRepository, UserRepository userRepository, PersonRepository personRepository, PasswordEncoder encoder) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run(String... args) throws Exception {
        RolBean adminRole = getOrSaveRole(new RolBean("ADMIN_ROLE"));
        getOrSaveRole(new RolBean("USER_ROLE"));
        getOrSaveRole(new RolBean("CLIENT_ROLE"));
        //Crear un usuario para que puedan iniciar sesión (person, user, user_role)
        PersonBean person = getOrSavePerson(
                new PersonBean("mike", "moreno", "Velazquez",
                        LocalDate.of(1998, 1, 19), "MOVM980119HM", "alguien@example.com")
        );
        UserBean user = getOrSaveUser(
                new UserBean("admin", encoder.encode("admin"), person)
        );
        saveUserRoles(user.getId(), adminRole.getId());
    }

    @Transactional
    public RolBean getOrSaveRole(RolBean role) {
        Optional<RolBean> foundRole = rolRepository.findByRol(role.getRol());
        return foundRole.orElseGet(() -> rolRepository.saveAndFlush(role));
    }
    @Transactional
    public PersonBean getOrSavePerson(PersonBean person) {
        Optional<PersonBean> foundPerson = personRepository.findByCurp(person.getCurp());
        return foundPerson.orElseGet(() -> personRepository.saveAndFlush(person));
    }
    @Transactional
    public UserBean getOrSaveUser(UserBean user) {
        Optional<UserBean> foundUser = userRepository.findFirstByUsername(user.getUsername());
        return foundUser.orElseGet(() -> userRepository.saveAndFlush(user));
    }
    @Transactional
    public void saveUserRoles(Long id, Long roleId) {
        Long userRoleId = userRepository.getIdUserRoles(id, roleId);
        if (userRoleId == null)
            userRepository.saveUserRole(id, roleId);
    }
}
