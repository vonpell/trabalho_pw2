package com.material.repository;

import com.material.model.Material;
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
public class MaterialRepository {
    private static final String DB_FILE = "src/main/resources/materialDatabase.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Material> materiais = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        carregarDados();
    }

    private void carregarDados() {
        try {
            File file = new File(DB_FILE);
            if (file.exists()) {
                JsonNode root = objectMapper.readTree(file);
                ArrayNode materiaisArray = (ArrayNode) root.get("materiais");
                
                materiais.clear();
                for (JsonNode node : materiaisArray) {
                    Material material = new Material();
                    material.setId(node.get("id").asText());
                    material.setNome(node.get("nome").asText());
                    material.setUnidadeMedida(node.get("unidadeMedida").asText());
                    material.setPrecoUnitario(new BigDecimal(node.get("precoUnitario").asText()));
                    materiais.put(material.getId(), material);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try {
            ObjectNode root = objectMapper.createObjectNode();
            ArrayNode materiaisArray = objectMapper.createArrayNode();
            
            for (Material material : materiais.values()) {
                ObjectNode materialNode = objectMapper.createObjectNode();
                materialNode.put("id", material.getId());
                materialNode.put("nome", material.getNome());
                materialNode.put("unidadeMedida", material.getUnidadeMedida());
                materialNode.put("precoUnitario", material.getPrecoUnitario());
                materiaisArray.add(materialNode);
            }
            
            root.set("materiais", materiaisArray);
            
            Files.createDirectories(Paths.get(DB_FILE).getParent());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_FILE), root);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public Material criar(Material material) {
        // Gera novo ID baseado no maior ID existente
        int maxId = materiais.keySet().stream()
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);
        material.setId(String.valueOf(maxId + 1));
        
        materiais.put(material.getId(), material);
        salvarDados();
        return material;
    }

    public Optional<Material> buscarPorId(String id) {
        return Optional.ofNullable(materiais.get(id));
    }

    public List<Material> listarTodos() {
        return new ArrayList<>(materiais.values());
    }

    public Optional<Material> atualizar(String id, Material material) {
        if (!materiais.containsKey(id)) {
            return Optional.empty();
        }
        material.setId(id);
        materiais.put(id, material);
        salvarDados();
        return Optional.of(material);
    }

    public boolean deletar(String id) {
        boolean removido = materiais.remove(id) != null;
        if (removido) {
            salvarDados();
        }
        return removido;
    }
}