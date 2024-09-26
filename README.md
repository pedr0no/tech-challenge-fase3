# Table Reservation - Projeto Tech Challenge FIAP Fase 3

Este projeto faz parte do **Tech Challenge FIAP - Fase 3**,
do curso **Pós Tech em Arquitetura e Desenvolvimento Java**.

Consiste em uma aplicação **API RESTful** de **reserva de mesas** de restaurantes,
e que também recebe **avaliações de clientes**.
Assim que a reserva é realizada,
o cliente **recebe um e-mail** com as devidas informações.

A aplicação foi desenvolvida utilizando padrões de **Clean Architecture**, **SOLID** ...
e as melhores práticas de desenvolvimento.

## Tecnologias Utilizadas

### Back-end:

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento.
- **Spring Boot 3.3.0**: Framework principal para construção da aplicação.
    - **Spring Boot Starter Web**: Para criação de APIs RESTful.
    - **Spring Boot Starter Data MongoDB**: Para integração com o banco de dados MongoDB.
    - **Spring Boot Starter Validation**: Para validação de dados de entrada.
    - **Spring Boot Starter Cache**: Para implementação de cache na aplicação.
    - **Spring Boot Starter Mail**: Para envio de e-mails.
- **Spring Cloud 2023.0.3**: Utilizado para gerenciamento de dependências e integração com microservices.
- **SpringDoc OpenAPI 2.6.0**: Para gerar automaticamente a documentação da API em formato OpenAPI (Swagger UI).
- **Lombok**: Simplificação de código com anotações (gera getters, setters, e mais).

### Testes:

- **Spring Boot Starter Test**: Para criação de testes unitários e de integração.

## Pré-requisitos

- **Git**:
    - [Git](https://git-scm.com/downloads)
- **Maven**:
    - [Maven](https://maven.apache.org/download.cgi)
- **Docker Desktop**:
    - [Docker](https://www.docker.com/products/docker-desktop/)

## Como executar a aplicação

### Passo 1: Clonar o repositório

Certifique-se de que o **Git** está instalado. Para clonar o repositório, use o comando:

```bash
git clone https://github.com/pedr0no/tech-challenge-fase3.git
```

Você também pode acessar diretamente o repositório pelo link
do [GitHub](https://github.com/pedr0no/tech-challenge-fase3).

### Passo 2: Gerar o executável (.jar) da aplicação

Certifique-se de ter o **Maven** instalado e adicionado no **PATH**.

No terminal, dentro do diretório raíz do projeto
(local onde se encontra o arquivo **pom.xml** da aplicação),
execute o comando abaixo para realizar a build da aplicação e gerar o arquivo
executável (**.jar**):

```bash
mvn clean package
```

### Passo 3: Executar a aplicação com o Docker Compose

Certifique-se de ter o **Docker Desktop** instalado.

No terminal, dentro do diretório raíz do projeto
(local onde se encontra o arquivo **pom.xml** da aplicação),
execute o comando abaixo para subir a aplicação e o banco de dados **MongoDB**:

```bash
docker-compose up -d
```

A aplicação estará disponível em

```
http://localhost:8080/tableReservation/api/
```

e estará se conectando com o container do banco de dados **MongoDB**.

## Documentação da API (Swagger UI)

A documentação da **API** pode ser acessada via **Swagger UI**. Após executar a aplicação, acesse:

```
http://localhost:8080/tableReservation/api/swagger-ui/index.html
```

Lá você encontrará detalhes sobre todos os endpoints disponíveis, parâmetros de entrada, e respostas.