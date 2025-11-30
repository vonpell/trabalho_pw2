# API de Gerenciamento de Mão de Obra

API REST desenvolvida com Quarkus para gerenciar serviços de mão de obra na construção civil.

## Tecnologias

- Quarkus 3.6.0
- Java 21
- REST com Jackson
- Armazenamento em JSON

## Estrutura do Projeto

```
src/main/java/com/exemplo/
├── model/
│   └── MaoDeObra.java
├── repository/
│   └── MaoDeObraRepository.java
├── service/
│   └── MaoDeObraService.java
└── resource/
    └── MaoDeObraResource.java

src/main/resources/
└── database.json (dados persistidos)
```

## Executar a Aplicação

### Modo de Desenvolvimento
```bash
./mvnw compile quarkus:dev
```

A aplicação roda na porta **8081** (diferente do serviço de materiais que usa 8080)

### Empacotar e Executar
```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

## Endpoints da API

Base URL: `http://localhost:8081/api/mao-de-obra`

### Criar Mão de Obra
```bash
POST /api/mao-de-obra
Content-Type: application/json

{
  "nome": "Carpinteiro",
  "unidadeMedida": "diária",
  "precoUnitario": 260.00
}
```

### Listar Todos
```bash
GET /api/mao-de-obra
```

### Buscar por ID
```bash
GET /api/mao-de-obra/{id}
```

### Atualizar
```bash
PUT /api/mao-de-obra/{id}
Content-Type: application/json

{
  "nome": "Pedreiro Especializado",
  "unidadeMedida": "diária",
  "precoUnitario": 300.00
}
```

### Deletar
```bash
DELETE /api/mao-de-obra/{id}
```

## Exemplos de Uso com cURL

### Criar um item
```bash
curl -X POST http://localhost:8081/api/mao-de-obra \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Gesseiro",
    "unidadeMedida": "diária",
    "precoUnitario": 220.00
  }'
```

### Listar todos
```bash
curl http://localhost:8081/api/mao-de-obra
```

### Buscar por ID
```bash
curl http://localhost:8081/api/mao-de-obra/1
```

### Atualizar
```bash
curl -X PUT http://localhost:8081/api/mao-de-obra/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Pedreiro Master",
    "unidadeMedida": "diária",
    "precoUnitario": 280.00
  }'
```

### Deletar
```bash
curl -X DELETE http://localhost:8081/api/mao-de-obra/5
```

## Modelo de Dados

**MaoDeObra**
- `id` (String): Identificador único gerado automaticamente
- `nome` (String): Nome do profissional/serviço
- `unidadeMedida` (String): Unidade de medida (ex: diária, hora, m²)
- `precoUnitario` (BigDecimal): Preço unitário do serviço

## Dados Mockados Iniciais

O arquivo `database.json` vem com 5 exemplos:
1. Pedreiro - R$ 250,00/diária
2. Pintor - R$ 200,00/diária
3. Eletricista - R$ 280,00/diária
4. Encanador/Hidráulico - R$ 270,00/diária
5. Servente - R$ 150,00/diária

## Observações

- Os dados são armazenados no arquivo `src/main/resources/database.json`
- O arquivo é atualizado automaticamente a cada operação CRUD
- Os IDs são gerados automaticamente de forma incremental
- A aplicação roda na porta **8081** para não conflitar com outros serviços
- CORS está habilitado para desenvolvimento
- Ao iniciar, os dados do JSON são carregados automaticamente

## Integração com Outros Microserviços

Este serviço pode ser usado em conjunto com:
- **Materiais API** (porta 8080) - gerenciamento de materiais
- Futuros serviços de orçamentos, projetos, etc.