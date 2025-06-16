# 📟 ger-chamados

Sistema de gerenciamento de chamados desenvolvido com Java e Spring Boot, utilizando PostgreSQL como banco de dados, Swagger para documentação de API e Docker para conteinerização.

---

## 🚀 Apresentação

O projeto **ger-chamados** tem como objetivo oferecer uma API REST para controle de chamados. Ele foi construído com as seguintes tecnologias:

- **Java 17**: Linguagem de programação principal do projeto.
- **Spring Boot 3.5.0**: Framework para facilitar o desenvolvimento de aplicações Java modernas.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os chamados.
- **Swagger UI (Springdoc OpenAPI)**: Utilizado para gerar documentação interativa da API.
- **Docker**: Plataforma de conteinerização para facilitar o deploy e execução da aplicação.

---

## 📚 Endpoints da API

| Método HTTP | Endpoint                       | Descrição                                                        |
|-------------|--------------------------------|------------------------------------------------------------------|
| `PUT`       | `/chamados/atualizar`          | Atualiza o status de um chamado e, opcionalmente, a descrição.   |
| `POST`      | `/chamados/novo-chamado`       | Cria um novo chamado.                                            |
| `GET`       | `/chamados`                    | Lista todos os chamados. Pode ser filtrado por Status do chamado |
| `GET`       | `/chamados/{idChamado}`        | Retorna os detalhes de um chamado específico pelo ID.            |
| `DELETE`    | `/chamados/{idChamado}`        | Remove um chamado específico pelo ID.                            |

> ℹ️ Os endpoints estão disponíveis por padrão em: `http://localhost:8080`

---

## 🐳 Como executar a aplicação com Docker

1. **Clone o repositório:**
```
   git clone https://github.com/cassiocastro73/ger-chamados.git
   cd ger-chamados
```

2. **Faça o build e construa a imagem Docker da aplicação:**
```
./mvnw clean package -DskipTests
docker build -t ger-chamados .
```
3. **Suba os containers com Docker Compose:**
```
docker-compose up
```
**Ou execute manualmente os containers:**

# Subir banco de dados PostgreSQL
```
docker run --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=chamados -p 5432:5432 -d postgres
```
# Subir a aplicação
```
docker run --name spring_app --link postgres-db:chamados -p 8080:8080 ger-chamados
```

## 📄 Documentação da API
A documentação interativa da API está disponível via Swagger UI:

📎 http://localhost:8080/swagger-ui.html
