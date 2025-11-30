package com.orcamento.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.orcamento.model.Orcamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class OrcamentoRepository {

    private static final String DATABASE_FILE = "src/main/resources/database.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Database database;
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        carregarDatabase();
    }

    private void carregarDatabase() {
        File file = new File(DATABASE_FILE);
        try {
            if (file.exists()) {
                database = objectMapper.readValue(file, Database.class);
                if (database.orcamentos == null) {
                    database.orcamentos = new ArrayList<>();
                }
                // Atualizar o gerador de IDs
                database.orcamentos.stream()
                    .mapToLong(o -> o.id)
                    .max()
                    .ifPresent(maxId -> idGenerator.set(maxId + 1));
            } else {
                database = new Database();
                salvarDatabase();
            }
        } catch (IOException e) {
            database = new Database();
        }
    }

    private void salvarDatabase() {
        try {
            File file = new File(DATABASE_FILE);
            file.getParentFile().mkdirs();
            objectMapper.writerWithDefaultPrettyPrinter()
                       .writeValue(file, database);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar database", e);
        }
    }

    public Orcamento criar(Orcamento orcamento) {
        orcamento.id = idGenerator.getAndIncrement();
        database.orcamentos.add(orcamento);
        salvarDatabase();
        return orcamento;
    }

    public List<Orcamento> listarTodos() {
        return new ArrayList<>(database.orcamentos);
    }

    public Optional<Orcamento> buscarPorId(Long id) {
        return database.orcamentos.stream()
            .filter(o -> o.id.equals(id))
            .findFirst();
    }

    public Orcamento atualizar(Long id, Orcamento orcamentoAtualizado) {
        Optional<Orcamento> existente = buscarPorId(id);
        if (existente.isPresent()) {
            database.orcamentos.remove(existente.get());
            orcamentoAtualizado.id = id;
            database.orcamentos.add(orcamentoAtualizado);
            salvarDatabase();
            return orcamentoAtualizado;
        }
        return null;
    }

    public boolean deletar(Long id) {
        boolean removido = database.orcamentos.removeIf(o -> o.id.equals(id));
        if (removido) {
            salvarDatabase();
        }
        return removido;
    }

    private static class Database {
        public List<Orcamento> orcamentos = new ArrayList<>();
    }
}
