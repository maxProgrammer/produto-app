package com.api.produtoapp.models;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="TB_Produto")
public class ProdutoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private long quantidade;
    private double valor;
}
