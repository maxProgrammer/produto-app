package com.api.produtoapp.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProdutoDto {

    @NotBlank
    private String nome;
    @NotNull
    private long quantidade;
    @NotNull
    private double valor;
}
