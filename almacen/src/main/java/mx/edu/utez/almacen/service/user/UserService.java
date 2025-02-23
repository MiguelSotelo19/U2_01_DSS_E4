package mx.edu.utez.almacen.service.user;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.model.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    public Optional<UserBean> findUserByUsername(String username){
        return repository.findFirstByUsername(username);
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK, "Busqueda realizada"), HttpStatus.OK);
    }
}
