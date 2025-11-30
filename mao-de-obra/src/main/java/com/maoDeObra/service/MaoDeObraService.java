package com.maoDeObra.service;

import com.maoDeObra.model.MaoDeObra;
import com.maoDeObra.repository.MaoDeObraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MaoDeObraService {
    
    @Inject
    MaoDeObraRepository repository;

    public MaoDeObra criar(MaoDeObra item) {
        return repository.criar(item);
    }

    public Optional<MaoDeObra> buscarPorId(String id) {
        return repository.buscarPorId(id);
    }

    public List<MaoDeObra> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<MaoDeObra> atualizar(String id, MaoDeObra item) {
        return repository.atualizar(id, item);
    }

    public boolean deletar(String id) {
        return repository.deletar(id);
    }
}
