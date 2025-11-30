package com.maoDeObra.repository;

import com.maoDeObra.model.MaoDeObra;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@ApplicationScoped
public class MaoDeObraRepository {
    private static final String DB_FILE = "src/main/resources/maoDeObraDatabase.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, MaoDeObra> maoDeObra = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        carregarDados();
    }

    private void carregarDados() {
        try {
            File file = new File(DB_FILE);
            if (file.exists()) {
                JsonNode root = objectMapper.readTree(file);
                ArrayNode maoDeObraArray = (ArrayNode) root.get("maoDeObra");
                
                maoDeObra.clear();
                for (JsonNode node : maoDeObraArray) {
                    MaoDeObra item = new MaoDeObra();
                    item.setId(node.get("id").asText());
                    item.setNome(node.get("nome").asText());
                    item.setUnidadeMedida(node.get("unidadeMedida").asText());
                    item.setPrecoUnitario(new BigDecimal(node.get("precoUnitario").asText()));
                    maoDeObra.put(item.getId(), item);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try {
            ObjectNode root = objectMapper.createObjectNode();
            ArrayNode maoDeObraArray = objectMapper.createArrayNode();
            
            for (MaoDeObra item : maoDeObra.values()) {
                ObjectNode itemNode = objectMapper.createObjectNode();
                itemNode.put("id", item.getId());
                itemNode.put("nome", item.getNome());
                itemNode.put("unidadeMedida", item.getUnidadeMedida());
                itemNode.put("precoUnitario", item.getPrecoUnitario());
                maoDeObraArray.add(itemNode);
            }
            
            root.set("maoDeObra", maoDeObraArray);
            
            Files.createDirectories(Paths.get(DB_FILE).getParent());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_FILE), root);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public MaoDeObra criar(MaoDeObra item) {
        int maxId = maoDeObra.keySet().stream()
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);
        item.setId(String.valueOf(maxId + 1));
        
        maoDeObra.put(item.getId(), item);
        salvarDados();
        return item;
    }

    public Optional<MaoDeObra> buscarPorId(String id) {
        return Optional.ofNullable(maoDeObra.get(id));
    }

    public List<MaoDeObra> listarTodos() {
        return new ArrayList<>(maoDeObra.values());
    }

    public Optional<MaoDeObra> atualizar(String id, MaoDeObra item) {
        if (!maoDeObra.containsKey(id)) {
            return Optional.empty();
        }
        item.setId(id);
        maoDeObra.put(id, item);
        salvarDados();
        return Optional.of(item);
    }

    public boolean deletar(String id) {
        boolean removido = maoDeObra.remove(id) != null;
        if (removido) {
            salvarDados();
        }
        return removido;
    }
}
