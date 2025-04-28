# Orders API

API REST para gerenciamento de pedidos, produtos e pagamentos.

Projetado com arquitetura limpa, segurança JWT, documentação Swagger, e seguindo boas práticas.

---

## 📈 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Springdoc OpenAPI (Swagger UI)

---

## 🔧 Instalação

1. Clone o repositório:

```bash
git clone https://github.com/OtavioIdem/springboot.git
```

2. Configure o banco de dados PostgreSQL:

Crie um banco chamado `orders_db`.

Exemplo de configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/orders_db
spring.datasource.username=postgres
spring.datasource.password=123456
```

3. Rode o projeto:

```bash
mvn clean spring-boot:run
```

---

## 🔐 Autenticação (JWT)

### Usuários padrões para login:

| Usuário | Senha | Perfil |
|----------|-------|--------|
| admin    | admin123 | ADMIN |
| cliente  | cliente123 | CLIENTE |
| operador | operador123 | OPERADOR |

### Gerar Token:

**POST** `/api/auth/login`

Enviar parâmetros:

```http
POST /api/auth/login?username=admin&password=admin123
```

Retorno:

```json
"eyJhbGciOiJIUzI1NiJ9..."
```

### Usar o Token:

Enviar o token no Header das requisições:

```http
Authorization: Bearer seu_token_aqui
```

---

## 🔹 Endpoints Principais

### Produtos

- `GET /api/products`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`
- `GET /api/products/search/name?name=batata`
- `GET /api/products/search/category?category=alimento`
- `GET /api/products/search/price?min=10&max=100`

### Pedidos

- `GET /api/orders`
- `POST /api/orders`
- `PUT /api/orders/{id}`
- `DELETE /api/orders/{id}`
- `GET /api/orders/search/status?status=PAGO`
- `GET /api/orders/search/date?start=2025-04-01T00:00:00&end=2025-04-30T23:59:59`
- `GET /api/orders/search/total?min=100&max=500`

### Pagamentos

- `POST /api/payments`
- `GET /api/payments/{id}`

### Relatórios

- `GET /api/reports/sales-by-date?start=2025-04-01T00:00:00&end=2025-04-30T23:59:59`
- `GET /api/reports/sales-by-status?status=PAGO`


---

## 📘 Swagger UI (Documentação da API)

Após subir o projeto:

Acesse:

```http
http://localhost:8080/swagger-ui.html
```

Você pode testar todos os endpoints diretamente pela interface Swagger.

---

## 🌐 Configurações Avançadas (Opcional)

- Implementar testes unitários e de integração
- Parametrizar taxas de frete e desconto
- Integrar RabbitMQ para mensageria
- Dockerizar aplicação e PostgreSQL
- CI/CD com GitHub Actions

---

## ✨ Projeto desenvolvido com foco em:

- Arquitetura limpa
- Padrões SOLID
- Segurança JWT
- Código limpo e organizado
- Documentação automática

---

# 🚀 Bom desenvolvimento!

