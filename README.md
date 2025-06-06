# Imobiliária Web 🏡

Projeto desenvolvido como protótipo para uma imobiliária fictícia. O sistema permite o **cadastro e gerenciamento de imóveis, corretores e anúncios**. Foi construído utilizando **Spring Boot**, **Thymeleaf** e um banco de dados relacional.

## ✅ Funcionalidades

- CRUD de **Corretores**
- CRUD de **Imóveis**
- CRUD de **Anúncios**
- Associação de um **corretor a cada anúncio**
- Associação de **um imóvel por anúncio**
- Filtro de anúncios por **nome do corretor** e **bairro**
- Interface web com **formulários e listagens**
- Sem autenticação

---

## ⚙️ Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL (ou outro SGBD relacional gratuito)
- Maven
- Docker (opcional)

---

## 📦 Como executar o projeto

### 🔽 Clonando o repositório

git clone https://github.com/p-pedroSantos/imobiliaria-web.git
cd imobiliaria-web

### Executando com Java (sem Docker)
## Configure seu banco de dados (ex: MySQL) e crie um schema com o nome que desejar (ex: imobiliaria).

Atualize o arquivo src/main/resources/application.properties com as credenciais do banco:

properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost:3306/imobiliaria
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

Compile e execute:
./mvnw spring-boot:run

### 🐳 Executando com Docker (Recomendado)
## Requer Docker e Docker Compose instalados.

Execute o comando:

docker-compose up --build
A aplicação estará disponível em:

App: http://localhost:8080

Banco MySQL: porta 3306

🗂 Estrutura principal do projeto
css
Copiar
Editar
imobiliaria-web/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ... (controllers, services, models, repositories)
│   │   └── resources/
│   │       ├── templates/ (HTMLs Thymeleaf)
│   │       └── application.properties
├── Dockerfile
├── docker-compose.yaml
├── pom.xml
└── README.md
