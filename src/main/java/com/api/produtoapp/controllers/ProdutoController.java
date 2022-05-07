package com.api.produtoapp.controllers;

import com.api.produtoapp.dtos.ProdutoDto;
import com.api.produtoapp.models.ProdutoModel;
import com.api.produtoapp.services.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("cadastrar-produto")
    public ResponseEntity<ProdutoModel> cadastrarProduto(@RequestBody @Valid ProdutoDto produtoDto){
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto,produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }


}
