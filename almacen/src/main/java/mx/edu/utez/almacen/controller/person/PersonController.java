package mx.edu.utez.almacen.controller.person;


import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.person.dto.PersonDto;
import mx.edu.utez.almacen.service.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/almacen/person")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class PersonController {
    private final PersonService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse>getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register( @RequestBody PersonDto dto){
        return service.save(dto.toEntity());
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> update(@RequestBody PersonDto dto){
        return service.update(dto.toEntityId());
    }
}
