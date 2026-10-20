# MovieFlix

O MovieFlix é uma API que funciona como um catálogo de filmes. Nela você cadastra filmes, organiza cada um em categorias (Ação, Drama, Ficção científica...) e informa em quais serviços de streaming ele está disponível (Netflix, Prime Video...). Depois é só consultar o catálogo, inclusive filtrando os filmes por categoria.

Este repositório contém o backend da aplicação.

## Como funciona

### Filmes
Cada filme tem título, descrição, avaliação, data de lançamento, uma ou mais categorias e um ou mais serviços de streaming. É possível listar todos os filmes, buscar um filme pelo identificador, buscar os filmes de uma categoria, cadastrar, atualizar e remover.

### Categorias e streamings
São os cadastros de apoio do catálogo. Cada um tem apenas um nome, e os dois têm as mesmas operações: listar, buscar por identificador, cadastrar, atualizar e remover. Ao cadastrar um filme, você informa os identificadores das categorias e dos streamings que já existem.

### Usuários e login
Para usar o catálogo é preciso ter uma conta:

1. Você se cadastra informando nome, email e senha. A senha precisa ter pelo menos 6 caracteres e é guardada criptografada. Não é possível cadastrar dois usuários com o mesmo email.
2. Você faz login com email e senha e recebe um token JWT, válido por 24 horas.
3. Em todas as outras requisições você envia esse token no cabeçalho `Authorization: Bearer <token>`.

Só o cadastro, o login e a documentação são públicos. O resto do sistema devolve `401` para quem não estiver autenticado.

## Rotas

| Recurso | Rotas |
|---|---|
| Autenticação | `POST /movieflix/auth/registro`, `POST /movieflix/auth/login` |
| Filmes | `GET /movieflix/filmes`, `GET /movieflix/filmes/{id}`, `GET /movieflix/filmes/procurar?categoria={id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| Categorias | `GET /movieflix/categorias`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| Streamings | `GET /movieflix/streamings`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |

Exemplo de cadastro de filme:

```json
{
  "titulo": "Interestelar",
  "descricao": "Exploradores viajam através de um buraco de minhoca em busca de um novo lar para a humanidade",
  "avaliacao": 8.7,
  "lancamento": "07/11/2014",
  "categorias": [1, 2],
  "streamings": [1]
}
```

A data de lançamento usa o formato `dd/MM/yyyy`.

### Respostas de erro
- `400`: dados inválidos (a resposta lista o que está errado em cada campo) ou usuário/senha incorretos no login
- `401`: requisição sem token válido
- `404`: o item buscado não existe
- `409`: email já cadastrado

## Documentação interativa

Com a aplicação rodando, a documentação completa das rotas fica no Swagger, onde também dá para testar as requisições:

- Swagger UI: http://localhost:8080/swagger/index.html
- OpenAPI (JSON): http://localhost:8080/api/api-docs

## Tecnologias

- Java 25 e Spring Boot 4
- Spring Security com JWT (java-jwt) e senhas com BCrypt
- Spring Data JPA e PostgreSQL
- Flyway para versionar o banco de dados
- Bean Validation para validar as requisições
- springdoc-openapi (Swagger)
- Maven

## Como rodar

**Pré-requisitos:** JDK 25 e PostgreSQL.

1. Crie um banco chamado `movieflix` no PostgreSQL (o padrão é `localhost:5432`, usuário `postgres`).
2. Crie um arquivo `.env` na raiz do projeto com:

   ```
   DB_PASSWORD=sua_senha_do_postgres
   JWT_SECRET=um_segredo_longo_para_assinar_os_tokens
   ```

3. Inicie a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

As tabelas são criadas automaticamente pelo Flyway na primeira execução, e a API sobe em `http://localhost:8080`.

## Estrutura do projeto

```
src/main/java/com/ufn/movieflix
├── controller   rotas da API, com os requests e responses
├── service      regras de negócio
├── repository   acesso ao banco de dados
├── entity       entidades JPA (Filme, Categoria, Streaming, Usuario)
├── mapper       conversão entre entidades e requests/responses
├── config       segurança, JWT, Swagger e tratamento de erros
└── exception    exceções da aplicação

src/main/resources/db/migration   scripts SQL do Flyway
```
