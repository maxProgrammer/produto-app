package com.api.produtoapp.controllers;

import com.api.produtoapp.dtos.ProdutoDto;
import com.api.produtoapp.models.ProdutoModel;
import com.api.produtoapp.services.ProdutoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@OpenAPIDefinition(info = @Info(title = "Product API", version = "1.0", description = "REST API responsible to allow CRUD Products."))
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Operation(summary = "Add a product")
    @PostMapping("addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid ProdutoDto produtoDto) {
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
    }


    @Operation(summary = "Return Product List")
    @GetMapping("/allProducts")
    public ResponseEntity<List<ProdutoModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }


    @Operation(summary = "Return an unique Product")
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getOneProdduct(@PathVariable(value = "id") Long id) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());
    }


    @Operation(summary = "Update a unique product")
    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id, @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        produtoModel.setId(produtoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
    }

    @Operation(summary = "Delete a unique Product")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> delProduct(@PathVariable(value = "id") Long id) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        produtoService.delete(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parsing Product deleted Successfully");
    }

}
