package com.api.produtoapp.services;

import com.api.produtoapp.models.ProdutoModel;
import com.api.produtoapp.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoModel save(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }


    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }


    public Optional<ProdutoModel> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public void delete(ProdutoModel produtoModel) {
        produtoRepository.delete(produtoModel);
    }
}
