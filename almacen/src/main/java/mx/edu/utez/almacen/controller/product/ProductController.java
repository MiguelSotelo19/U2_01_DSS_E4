package mx.edu.utez.almacen.controller.product;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.person.dto.PersonDto;
import mx.edu.utez.almacen.controller.product.dto.ProductDto;
import mx.edu.utez.almacen.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacen/product")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse>getAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@RequestBody ProductDto dto){
        return service.save(dto.toEntity());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id){
        return service.findById(id);
    }

}
