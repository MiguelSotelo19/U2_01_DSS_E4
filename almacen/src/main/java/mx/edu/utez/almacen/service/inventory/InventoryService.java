package mx.edu.utez.almacen.service.inventory;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.inventory.InventoryRepository;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.product.ProductBean;
import mx.edu.utez.almacen.model.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    private final ProductRepository productRepo;

    @Transactional
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK, "Todo bien"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id){
        return new ResponseEntity<>(new ApiResponse(repository.findById(id).orElse(null), HttpStatus.OK, "Se encontró el registro"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> save(InventoryBean inventoryBean){
        Optional<ProductBean> productfound = productRepo.findById(inventoryBean.getProductBean().getId());
        if (productfound.isPresent()){
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(inventoryBean), HttpStatus.OK, "Guardado Exitosamente"), HttpStatus.OK);
        } else {
            //ProductBean product = productRepo.getOne(inventoryBean.getId());
            //new ResponseEntity<>(new ApiResponse(productRepo.saveAndFlush(product), HttpStatus.OK, "Se registro el producto"), HttpStatus.OK);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El producto ingresaado no existe"), HttpStatus.BAD_REQUEST);
        }

    }
}
