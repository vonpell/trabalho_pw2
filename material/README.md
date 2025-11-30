# API de Gerenciamento de Materiais

API REST desenvolvida com Quarkus para gerenciar materiais de construção.

## Tecnologias

- Quarkus 3.6.0
- Java 21
- REST com Jackson
- Armazenamento em JSON

## Estrutura do Projeto

```
src/main/java/com/exemplo/
├── model/
│   └── Material.java
├── repository/
│   └── MaterialRepository.java
├── service/
│   └── MaterialService.java
└── resource/
    └── MaterialResource.java

src/main/resources/
└── database.json (dados persistidos)
```

## Executar a Aplicação

### Modo de Desenvolvimento
```bash
./mvnw compile quarkus:dev
```

### Empacotar e Executar
```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

## Endpoints da API

Base URL: `http://localhost:8080/api/materiais`

### Criar Material
```bash
POST /api/materiais
Content-Type: application/json

{
  "nome": "Cimento",
  "unidadeMedida": "saco",
  "precoUnitario": 35.90
}
```

### Listar Todos os Materiais
```bash
GET /api/materiais
```

### Buscar Material por ID
```bash
GET /api/materiais/{id}
```

### Atualizar Material
```bash
PUT /api/materiais/{id}
Content-Type: application/json

{
  "nome": "Cimento Portland",
  "unidadeMedida": "saco",
  "precoUnitario": 38.50
}
```

### Deletar Material
```bash
DELETE /api/materiais/{id}
```

## Exemplos de Uso com cURL

### Criar um material
```bash
curl -X POST http://localhost:8080/api/materiais \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Areia",
    "unidadeMedida": "m³",
    "precoUnitario": 120.00
  }'
```

### Listar todos
```bash
curl http://localhost:8080/api/materiais
```

### Buscar por ID
```bash
curl http://localhost:8080/api/materiais/{id}
```

### Atualizar
```bash
curl -X PUT http://localhost:8080/api/materiais/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Areia Lavada",
    "unidadeMedida": "m³",
    "precoUnitario": 130.00
  }'
```

### Deletar
```bash
curl -X DELETE http://localhost:8080/api/materiais/{id}
```

## Modelo de Dados

**Material**
- `id` (String): Identificador único gerado automaticamente
- `nome` (String): Nome do material
- `unidadeMedida` (String): Unidade de medida (ex: kg, m³, saco)
- `precoUnitario` (BigDecimal): Preço unitário do material

## Observações

- Os dados são armazenados no arquivo `src/main/resources/database.json`
- O arquivo é atualizado automaticamente a cada operação de criação, atualização ou exclusão
- Os IDs são gerados automaticamente de forma incremental
- A API retorna JSON em todos os endpoints
- CORS está habilitado para desenvolvimento
- Ao iniciar a aplicação, os dados do arquivo JSON são carregados automaticamente