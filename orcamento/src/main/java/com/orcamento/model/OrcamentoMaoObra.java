package com.orcamento.model;

import java.math.BigDecimal;

public class OrcamentoMaoObra {
    
    public String maoObraId;
    public String nomeMaoObra;
    public Integer quantidade;
    public BigDecimal precoUnitario;
    public BigDecimal valorTotal;
    public String unidadeMedida;

    public OrcamentoMaoObra() {
    }

    public OrcamentoMaoObra(String maoObraId, String nomeMaoObra, Integer quantidade, 
                           BigDecimal precoUnitario, String unidadeMedida) {
        this.maoObraId = maoObraId;
        this.nomeMaoObra = nomeMaoObra;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.unidadeMedida = unidadeMedida;
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
    }
}