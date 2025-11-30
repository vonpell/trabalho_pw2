package com.orcamento.model;

import java.math.BigDecimal;

public class OrcamentoMaoObra {
    
    public Long maoObraId;
    public String nomeMaoObra;
    public Integer quantidade;
    public BigDecimal precoUnitario;
    public BigDecimal valorTotal;
    public String unidade;

    public OrcamentoMaoObra() {
    }

    public OrcamentoMaoObra(Long maoObraId, String nomeMaoObra, Integer quantidade, 
                           BigDecimal precoUnitario, String unidade) {
        this.maoObraId = maoObraId;
        this.nomeMaoObra = nomeMaoObra;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
    }
}