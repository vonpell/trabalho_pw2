package com.material.service;

import com.material.model.Material;
import com.material.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MaterialService {
    
    @Inject
    MaterialRepository repository;

    public Material criar(Material material) {
        return repository.criar(material);
    }

    public Optional<Material> buscarPorId(String id) {
        return repository.buscarPorId(id);
    }

    public List<Material> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Material> atualizar(String id, Material material) {
        return repository.atualizar(id, material);
    }

    public boolean deletar(String id) {
        return repository.deletar(id);
    }
}