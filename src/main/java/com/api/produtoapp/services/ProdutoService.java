package com.api.produtoapp.services;

import com.api.produtoapp.models.ProdutoModel;
import com.api.produtoapp.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    @Transactional
    public ProdutoModel save(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }
}
