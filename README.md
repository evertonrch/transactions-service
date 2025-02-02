# API de Transações

## Descrição
Esta é uma API REST que permite o registro de transações financeiras com valor e data/hora, bem como a consulta de estatísticas das transações realizadas nos últimos 60 segundos. 
O projeto foi desenvolvido como parte de um desafio proposto pelo Itaú Unibanco. Todos os requisitos da proposta podem ser encontrados [aqui](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior).


## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Junit & Mockito**
- **Docker**
- **Swagger**

## Padrões de Projeto Aplicados
- **Arquitetura em camadas**
- **Builder Pattern**
- **Static Factory Method** 
- **DTOs (Data Transfer Objects)** 

## Endpoints da API
### Criar uma transação
```
POST /transacao
```
**Requisição:**
```
{
  "valor": 100.50,
  "dataHora": "2025-02-01T15:03:00-03:00"
}
```
**Respostas:**
- **201 Created**: Transação registrada com sucesso
- **400 Bad Request**: Requisição inválida
- **422 UNPROCESSABLE ENTITY**: Não atendeu às regras de negócio

---

### Listar transações
```
GET /transacao
```
**Respostas:**
- **200 OK**: Retorna a lista de transações registradas
- **204 No Content**: Nenhuma transação encontrada

---

### Excluir todas as transações
```
DELETE /transacao
```
**Respostas:**
- **200 OK**: Todas as transações foram removidas

---

### Obter estatísticas das transações
```
GET /estatistica
```
**Resposta de exemplo:**
```
{
  "max": 500.00,
  "min": 10.00,
  "avg": 250.00,
  "count": 5,
  "sum": 1250.00
}
```
**Respostas:**
- **200 OK**: Retorna as estatísticas das transações nos últimos 60 segundos ou todos os dados zerados

## Como Executar o Projeto

### Rodando localmente com Maven
```
mvn spring-boot:run
```

### Executando com Docker

1. **Criar a imagem Docker:**
```
docker build -t transacoes-api .
```

2. **Executar o container:**
```
docker run --name api -d -p 8080:8080 transacoes-api
```

### Acessando a Documentação (Swagger)
A API possui documentação interativa no Swagger, que pode ser acessada após iniciar a aplicação:
```
http://localhost:8080/swagger-ui.html
```

## Testes Automatizados
Para executar os testes unitários, utilize o seguinte comando:
```
mvn test
```

### 📌 Autor
**Everton Rocha**  
📧 [imevertonrch@gmail.com](mailto:imevertonrch@gmail.com)

