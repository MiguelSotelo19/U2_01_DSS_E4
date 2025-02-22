package mx.edu.utez.almacen.service.product;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.category.CategoryRepository;
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
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK, "Datos obtenidos exitosamente"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id){
        return new ResponseEntity<>(new ApiResponse(repository.findById(id).orElse(null), HttpStatus.OK, "Se encontró el registro"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> save(ProductBean product){
        Optional<ProductBean> foundProduct = repository.findById(product.getId());
        if (foundProduct.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "Registro duplicado"), HttpStatus.BAD_REQUEST);
        if (product.getCategory() != null){
            Optional<CategoryBean> foundCategory = categoryRepository.findById(product.getCategory().getId());
            if (foundCategory.isPresent()){
                return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(product),HttpStatus.OK,"Registro exitoso"), HttpStatus.OK);
            } else {
                new ResponseEntity<>(new ApiResponse(categoryRepository.saveAndFlush(product.getCategory()), HttpStatus.OK, "Se regsitro la categoría"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(product),HttpStatus.OK, "Guardado exitosamente"), HttpStatus.OK);
    }

}
