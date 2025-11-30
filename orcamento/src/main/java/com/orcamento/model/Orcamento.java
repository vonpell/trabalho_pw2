package com.orcamento.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Orcamento {
    
    public Long id;
    public String descricao;
    public LocalDateTime dataCriacao;
    public LocalDateTime dataAtualizacao;
    public List<OrcamentoMaterial> materiais = new ArrayList<>();
    public List<OrcamentoMaoObra> maosObra = new ArrayList<>();
    public BigDecimal valorTotal;

    public Orcamento() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
    }

    public void calcularValorTotal() {
        BigDecimal totalMateriais = materiais.stream()
            .map(m -> m.valorTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalMaoObra = maosObra.stream()
            .map(m -> m.valorTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = totalMateriais.add(totalMaoObra);
        this.dataAtualizacao = LocalDateTime.now();
    }
}