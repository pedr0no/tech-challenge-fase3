# Table Reservation - Projeto Tech Challenge FIAP Fase 3

Este projeto faz parte do **Tech Challenge FIAP - Fase 3**,
do curso **Pós Tech em Arquitetura e Desenvolvimento Java**.

Este projeto consiste em uma aplicação **API RESTful** que permite
aos clientes realizar reservas de mesas, avaliar restaurantes e
buscar por estabelecimentos de forma eficiente.
Após a confirmação da reserva, o cliente recebe
automaticamente um **e-mail** contendo todos os detalhes
relevantes.

A aplicação foi desenvolvida seguindo os princípios da **Clean
Architecture** e os padrões do **SOLID**, além de incorporar
**testes unitários**, de **integração**, de controle de **performance**
e de **controller**, garantindo alta qualidade e aderência às
melhores práticas de desenvolvimento.

## Tecnologias Utilizadas

### Back-end:

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento.
- **Spring Boot 3.3.0**: Framework principal para construção da aplicação.
- **Spring Web**: Para criação de APIs RESTful.
- **Spring Data MongoDB**: Para integração com o banco de dados MongoDB.
- **Spring Validation**: Para validação de dados de entrada.
- **Spring Cache**: Para implementação de cache na aplicação.
- **Spring Mail**: Para envio de e-mails.
- **Spring Cloud 2023.0.3**: Utilizado para gerenciamento de dependências e integração com microservices.
- **SpringDoc OpenAPI 2.6.0**: Para gerar automaticamente a documentação da API em formato OpenAPI (Swagger UI).
- **Lombok**: Simplificação de código com anotações (gera getters, setters, e mais).

### Testes:

- **Spring Test**: Para criação de testes unitários e de integração.
- **Mockito Core 5.5.0**: Para criar mocks em testes.
- **Mockito JUnit Jupiter 5.5.0**: Integração do Mockito com JUnit 5.
- **Cucumber Java 7.13.0**: Para testes de comportamento (BDD) com Cucumber.
- **Cucumber Spring 7.13.0**: Integração do Cucumber com Spring.
- **JUnit Platform Suite 1.9.3**: Para agrupar e executar diferentes testes na plataforma JUnit.
- **Cucumber JUnit Platform Engine 7.13.0**: Para integração entre Cucumber e JUnit.
- **RestAssured 5.3.0**: Para testes de APIs RESTful.
- **Testcontainers MongoDB 1.19.0**: Para testes com containers do MongoDB.
- **Testcontainers JUnit Jupiter 1.19.0**: Para integrar Testcontainers com JUnit.
- **Allure JUnit5 2.23.0**: Para geração de relatórios de teste.
- **Allure RestAssured 2.23.0**: Para integração do Allure com RestAssured em testes de API.
- **Gatling Core Java 3.12.0**: Para testes de performance.
- **Gatling Charts Highcharts 3.12.0**: Para geração de gráficos e relatórios de performance no Gatling.

## Pré-requisitos

- **Git**:
    - [Git](https://git-scm.com/downloads)
- **Docker Desktop** (para executar a aplicação através de containers):
    - [Docker](https://www.docker.com/products/docker-desktop/)
- **Maven** (somente para os testes):
    - [Maven](https://maven.apache.org/download.cgi)
- **MongoDB** (somente caso você execute a aplicação através da sua IDE, 
pois ela se conectará com o seu banco de dados local, se for executar com o Docker, não precisa):
    - [MongoDB](https://www.mongodb.com/try/download/community)
- **Cucumber Plugin**:
    - Baixar pela IDE

## Como executar a aplicação

### Passo 1: Clonar o repositório

Certifique-se de que o **Git** está instalado.

Para clonar o repositório, use o comando:

```bash
git clone https://github.com/pedr0no/tech-challenge-fase3.git
```

Você também pode acessar diretamente o repositório pelo link
do [GitHub](https://github.com/pedr0no/tech-challenge-fase3).

### Passo 2: Executar a aplicação com o Docker Compose

Certifique-se de ter o **Docker Desktop** instalado.

No terminal, dentro do diretório raíz do projeto
(local onde se encontra o arquivo **pom.xml** da aplicação),
execute o comando abaixo para subir a aplicação e o banco de dados **MongoDB**:

```bash
docker compose up -d
```

O Docker tentará baixar a imagem no repositório do 
[Docker Hub](https://hub.docker.com/repository/docker/welderessutti/tablereservation), 
caso ele não encontre a imagem, 
ele realizará a **build** da aplicação **(.jar)** e criará a imagem automaticamente 
**(isso pode levar em torno de 5 minutos)**.

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

## Como executar os testes:

Certifique-se de ter o **Maven** e o **Cucumber Plugin** instalados.

No terminal, dentro do diretório raíz do projeto
(local onde se encontra o arquivo **pom.xml** da aplicação),
execute os comandos abaixo para executar os testes.

- Para rodar os testes unitários (ativado por padrão):

```bash
mvn test
```

- Para rodar os testes de performance:

```bash
mvn test -P performanceTest
```

- Para rodar os testes de performance com o Gatling e gerar o relatório gráfico,
execute a aplicação na IDE e depois execute o comando:

```bash
mvn gatling:test -Pperformance-test
```

- Para rodar os testes de integração, executar o teste dentro da feature:
  **tech-challenge-fase3\tablereservation\src\test\resources\features**