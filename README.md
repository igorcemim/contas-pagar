# Contas a pagar

## Executar via Docker
```bash
docker-compose up
```
Acessar a API em `http://localhost:8080/contas`

## Executar pelo Gradle
- Alterar as configurações do banco MySQL no `application.properties`
- Executar o servidor embutido
```bash
./gradlew bootrun
```

## Métodos da API

### GET /contas

Corpo da resposta:
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Água",
      "valorOriginal": 132.58,
      "valorCorrigido": 135.36,
      "quantidadeDiasAtraso": 1,
      "dataPagamento": "2019-05-02",
      "dataVencimento": "2019-05-01",
      "percentualMulta": 2.00,
      "percentualJurosDia": 0.10
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "pageSize": 10,
    "pageNumber": 0,
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 1,
  "last": true,
  "totalElements": 1,
  "first": true,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "empty": false
}
```

Exemplo:
```bash
curl --request GET --url http://localhost:8080/contas
```

### POST /contas
Corpo da requisição:
```json
{
  "nome": "Água",
  "valorOriginal": 132.58,
  "dataPagamento": "2019-05-02",
  "dataVencimento": "2019-05-01"
}
```

Corpo da resposta:
```json
{
  "id": 1,
  "nome": "Água",
  "valorOriginal": 132.58,
  "valorCorrigido": 135.36,
  "quantidadeDiasAtraso": 1,
  "dataPagamento": "2019-05-02",
  "dataVencimento": "2019-05-01",
  "percentualMulta": 2.00,
  "percentualJurosDia": 0.10
}
```

Exemplo:

```bash
curl --request POST \
  --url http://localhost:8080/contas \
  --header 'content-type: application/json' \
  --data '{ "nome": "Água", "valorOriginal": 132.58, "dataPagamento": "2019-05-02", "dataVencimento": "2019-05-01" }'
```
