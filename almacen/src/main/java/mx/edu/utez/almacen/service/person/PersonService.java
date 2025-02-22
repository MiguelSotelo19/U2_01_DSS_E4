package mx.edu.utez.almacen.service.person;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.person.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK, "Todo bien"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id){
        return new ResponseEntity<>(new ApiResponse(repository.findById(id).orElse(null), HttpStatus.OK, "Se encontró el registro"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> save(PersonBean person){
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(person),HttpStatus.OK, "Guardado exitosamente"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(PersonBean person){
        Optional<PersonBean> foundPerson = repository.findById(person.getId());
        if (foundPerson.isPresent())
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(person),HttpStatus.OK, "Modificación exitosa"), HttpStatus.OK);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no exite"), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<ApiResponse> delete(PersonBean personBean){
        Optional<PersonBean> foundPerson = repository.findById(personBean.getId());
        if (foundPerson.isPresent()){
            repository.deleteById(personBean.getId());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false,"Se eliminó correctamente" ), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no exite"), HttpStatus.BAD_REQUEST);
        }
    }
}
