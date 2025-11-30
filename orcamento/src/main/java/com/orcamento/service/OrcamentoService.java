package com.orcamento.service;

import com.orcamento.client.MaterialClient;
import com.orcamento.dto.ItemMaoObraDTO;
import com.orcamento.dto.ItemMaterialDTO;
import com.orcamento.dto.MaoObraDTO;
import com.orcamento.dto.MaterialDTO;
import com.orcamento.dto.OrcamentoDTO;
import com.orcamento.client.MaoObraClient;
import com.orcamento.model.Orcamento;
import com.orcamento.model.OrcamentoMaterial;
import com.orcamento.model.OrcamentoMaoObra;
import com.orcamento.repository.OrcamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrcamentoService {

    @Inject
    OrcamentoRepository repository;

    @Inject
    @RestClient
    MaterialClient materialClient;

    @Inject
    @RestClient
    MaoObraClient maoObraClient;

    public Orcamento criar(OrcamentoDTO dto) {
        Orcamento orcamento = new Orcamento();
        orcamento.descricao = dto.descricao;

        // Processar materiais
        if (dto.materiais != null) {
            for (ItemMaterialDTO item : dto.materiais) {
                MaterialDTO material = materialClient.buscarPorId(item.materialId);
                OrcamentoMaterial orcMaterial = new OrcamentoMaterial(
                    material.id,
                    material.nome,
                    item.quantidade,
                    material.precoUnitario,
                    material.unidadeMedida
                );
                orcamento.materiais.add(orcMaterial);
            }
        }

        // Processar mão de obra
        if (dto.maosObra != null) {
            for (ItemMaoObraDTO item : dto.maosObra) {
                MaoObraDTO maoObra = maoObraClient.buscarPorId(item.maoObraId);
                OrcamentoMaoObra orcMaoObra = new OrcamentoMaoObra(
                    maoObra.id,
                    maoObra.nome,
                    item.quantidade,
                    maoObra.precoUnitario,
                    maoObra.unidadeMedida
                );
                orcamento.maosObra.add(orcMaoObra);
            }
        }

        orcamento.calcularValorTotal();
        return repository.criar(orcamento);
    }

    public List<Orcamento> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Orcamento> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Orcamento atualizar(Long id, OrcamentoDTO dto) {
        Optional<Orcamento> existente = repository.buscarPorId(id);
        if (existente.isEmpty()) {
            return null;
        }

        Orcamento orcamento = new Orcamento();
        orcamento.descricao = dto.descricao;
        orcamento.dataCriacao = existente.get().dataCriacao;

        // Processar materiais
        if (dto.materiais != null) {
            for (ItemMaterialDTO item : dto.materiais) {
                MaterialDTO material = materialClient.buscarPorId(item.materialId);
                OrcamentoMaterial orcMaterial = new OrcamentoMaterial(
                    material.id,
                    material.nome,
                    item.quantidade,
                    material.precoUnitario,
                    material.unidadeMedida
                );
                orcamento.materiais.add(orcMaterial);
            }
        }

        // Processar mão de obra
        if (dto.maosObra != null) {
            for (ItemMaoObraDTO item : dto.maosObra) {
                MaoObraDTO maoObra = maoObraClient.buscarPorId(item.maoObraId);
                OrcamentoMaoObra orcMaoObra = new OrcamentoMaoObra(
                    maoObra.id,
                    maoObra.nome,
                    item.quantidade,
                    maoObra.precoUnitario,
                    maoObra.unidadeMedida
                );
                orcamento.maosObra.add(orcMaoObra);
            }
        }

        orcamento.calcularValorTotal();
        return repository.atualizar(id, orcamento);
    }

    public boolean deletar(Long id) {
        return repository.deletar(id);
    }
}