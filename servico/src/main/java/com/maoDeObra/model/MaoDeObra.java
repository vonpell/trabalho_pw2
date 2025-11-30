package com.maoDeObra.model;

import java.math.BigDecimal;

public class MaoDeObra {
    private String id;
    private String nome;
    private String unidadeMedida;
    private BigDecimal precoUnitario;

    public MaoDeObra() {
    }

    public MaoDeObra(String nome, String unidadeMedida, BigDecimal precoUnitario) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.precoUnitario = precoUnitario;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}