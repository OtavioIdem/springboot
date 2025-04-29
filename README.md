# Orders API

API REST para gerenciamento de pedidos, produtos e pagamentos, utilizando Java 17 e Spring Boot 3.

---

## 📈 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- PostgreSQL
- RabbitMQ
- Docker / Docker Compose
- Lombok
- Springdoc OpenAPI (Swagger)

---

## 🔧 Instalação Local

### 1. Clone o repositório

```bash
git clone [https://github.com/seu-usuario/orders-api.git](https://github.com/OtavioIdem/springboot.git)
cd teste-backend
```

### 2. Configure seu Banco de Dados PostgreSQL

Antes de rodar a aplicação, é preciso criar seu próprio banco.

1. Suba um PostgreSQL localmente ou via Docker.
2. Crie um banco de dados chamado, por exemplo, `orders_db`.

**Exemplo de comandos SQL:**

```sql
CREATE DATABASE orders_db;
CREATE USER meu_usuario WITH ENCRYPTED PASSWORD 'minha_senha';
GRANT ALL PRIVILEGES ON DATABASE orders_db TO meu_usuario;
```

3. Ajuste as credenciais no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/orders_db
spring.datasource.username=meu_usuario
spring.datasource.password=minha_senha
spring.jpa.hibernate.ddl-auto=update
```


### 3. Configurar RabbitMQ (opcional)

Caso queira testar eventos, suba um container RabbitMQ localmente ou utilize o Docker Compose.

- URL padrão: `http://localhost:15672`
- Usuário: `guest` / Senha: `guest`

### 4. Rodar a aplicação

Se quiser rodar localmente:

```bash
mvn clean install
mvn spring-boot:run
```

Se quiser rodar via Docker Compose:

```bash
docker-compose up --build
```

---

## 🔐 Autenticação JWT

- **POST /api/auth/login**
- Parâmetros: `username`, `password`

Usuários disponíveis:

| Usuário | Senha | Perfil |
|----------|-------|--------|
| admin    | admin123 | ADMIN |
| cliente  | cliente123 | CLIENTE |
| operador | operador123 | OPERADOR |

Retorno:

```json
"Bearer eyJhbGciOiJIUzI1NiJ9..."
```

Use esse token no header das chamadas:

```http
Authorization: Bearer seu_token_aqui
```

---

## 🔹 Endpoints Principais

### Produtos
- CRUD de produtos
- Filtros por nome, categoria e preço

### Pedidos
- CRUD de pedidos
- Cálculo de frete fixo + desconto
- Filtros por status, data e valor

### Pagamentos
- CRUD de pagamentos

### Relatórios
- Vendas por data
- Vendas por status

---

## 📘 Documentação Swagger

Acesse a documentação da API em:

```http
http://localhost:8080/swagger-ui.html
```

Lá você pode testar todos os endpoints.

---

## 📦 Build Docker Manual (opcional)

```bash
mvn clean install
```

```bash
docker build -t orders-api .
```

```bash
docker run -p 8080:8080 orders-api
```

---

## 💪 Pipeline CI/CD

- Estava configurado o Github Actions, porém foi removido por questão de conflito com o repositório
<!-- O projeto já está preparado para GitHub Actions:
- Build do projeto
- Execução dos testes automáticos
- Verificação a cada push ou pull request na branch `main` -->

---

## ✨ Observação Final

> "Você pode manter as configurações de frete e desconto fixas no código para testes, mas para produção o ideal é parametrizar no `application.properties` para maior flexibilidade."

