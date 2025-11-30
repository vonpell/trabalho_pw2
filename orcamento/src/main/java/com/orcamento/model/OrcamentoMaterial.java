package com.orcamento.model;

import java.math.BigDecimal;

public class OrcamentoMaterial {
    
    public Long materialId;
    public String nomeMaterial;
    public Integer quantidade;
    public BigDecimal precoUnitario;
    public BigDecimal valorTotal;
    public String unidade;

    public OrcamentoMaterial() {
    }

    public OrcamentoMaterial(Long materialId, String nomeMaterial, Integer quantidade, 
                            BigDecimal precoUnitario, String unidade) {
        this.materialId = materialId;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
    }
}