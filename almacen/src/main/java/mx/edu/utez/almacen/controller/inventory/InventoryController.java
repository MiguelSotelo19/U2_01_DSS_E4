package mx.edu.utez.almacen.controller.inventory;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.inventory.dto.InventoryDto;
import mx.edu.utez.almacen.controller.product.dto.ProductDto;
import mx.edu.utez.almacen.service.inventory.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacen/inventory")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@RequestBody InventoryDto dto){
        return service.save(dto.toEntity());
    }
}
