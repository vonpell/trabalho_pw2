package com.orcamento.model;

import java.math.BigDecimal;

public class OrcamentoMaterial {
    
    public String materialId;
    public String nomeMaterial;
    public Integer quantidade;
    public BigDecimal precoUnitario;
    public BigDecimal valorTotal;
    public String unidadeMedida;

    public OrcamentoMaterial() {
    }

    public OrcamentoMaterial(String materialId, String nomeMaterial, Integer quantidade, 
                            BigDecimal precoUnitario, String unidadeMedida) {
        this.materialId = materialId;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.unidadeMedida = unidadeMedida;
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
    }
}