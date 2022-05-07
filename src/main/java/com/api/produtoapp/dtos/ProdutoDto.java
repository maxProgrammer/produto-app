package com.api.produtoapp.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProdutoDto {

    @NotNull
    private String nome;
    @NotNull
    private long quantidade;
    @NotNull
    private double valor;
}
