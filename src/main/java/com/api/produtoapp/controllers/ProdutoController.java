package com.api.produtoapp.controllers;

import com.api.produtoapp.dtos.ProdutoDto;
import com.api.produtoapp.models.ProdutoModel;
import com.api.produtoapp.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
@Api(value="API Rest Products")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("addProduct")
    @ApiOperation(value="Add a product")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid ProdutoDto produtoDto){
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto,produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
    }

    @GetMapping("/allProducts")
    @ApiOperation(value="Return Product List")
    public ResponseEntity<List<ProdutoModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/product/{id}")
    @ApiOperation(value="Return an unique Product")
    public ResponseEntity<Object> getOneProdduct(@PathVariable(value = "id") Long id){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if(!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());
    }

    @PutMapping("/product/{id}")
    @ApiOperation(value="Update a unique product")
    public ResponseEntity<Object> updateProduct(@PathVariable(value= "id")  Long id, @RequestBody @Valid ProdutoDto produtoDto){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if(!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto,produtoModel);
        produtoModel.setId(produtoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
    }

    @DeleteMapping("/product/{id}")
    @ApiOperation(value="Delete a unique Product")
    public ResponseEntity<Object>  delProduct(@PathVariable(value = "id")Long id){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if(!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        produtoService.delete(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parsing Product deleted Successfully");
    }



}
