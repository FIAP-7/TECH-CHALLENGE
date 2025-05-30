# 🍽️ Tech Challenge - Backend de Gestão para Restaurantes

Este projeto faz parte do **Tech Challenge** da Pós Graduação da FIAP, com o objetivo de desenvolver um backend robusto para gerenciamento de usuários em um sistema compartilhado entre restaurantes. A aplicação foi desenvolvida utilizando **Spring Boot** e está configurada para execução via **Docker Compose** com banco de dados relacional.

---

## 📌 Objetivo

Criar um sistema de backend completo e funcional com foco no **gerenciamento de usuários**, incluindo:

- Cadastro de usuários
- Atualização de dados
- Troca de senha
- Validação de login

Tipos de usuários:
- **Dono de restaurante**
- **Cliente**

---

## 🧰 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker & Docker Compose
- Lombok
- JWT (para autenticação)
- Insomnia (para testes de API)

---

## 📦 Estrutura do Projeto

- `controller/`: Camada de exposição dos endpoints
- `service/`: Lógica de negócio
- `repository/`: Interface de acesso ao banco de dados
- `model/`: Entidades da aplicação
- `dto/`: Objetos de transferência de dados
- `config/`: Configurações gerais (segurança, CORS, etc.)

---

## 🧪 Endpoints da API

| Método | Endpoint          | Descrição                        |
|--------|-------------------|----------------------------------|
| POST   | /auth/register     | Cadastro de novo usuário         |
| POST   | /auth/login        | Validação de login               |
| PUT    | /user/{id}         | Atualização de dados do usuário |
| PUT    | /user/{id}/password| Troca de senha do usuário       |

---

## 🗃️ Campos do Usuário

- `nome` (String)
- `email` (String)
- `login` (String)
- `senha` (String - criptografada)
- `dataUltimaAlteracao` (Date)
- `endereco` (String)
- `tipoUsuario` (Enum: CLIENTE, RESTAURANTE)

---

## 🚀 Executando o Projeto com Docker Compose

1. Clone o repositório:
```bash
git clone https://github.com/FIAP-7/TECH-CHALLENGE.git
cd TECH-CHALLENGE
```

2. Execute o Docker Compose:
```bash
docker-compose up --build
```

3. A aplicação estará disponível em:
```
http://localhost:8080
```

---

## 🔍 Testes de API

Utilize a [collection do Postman](./postman_collection.json) disponível no repositório para testar todos os endpoints da aplicação.

---

## 🧾 Documentação da API

Documentação detalhada dos endpoints está disponível via Swagger em:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📁 Repositório

Este repositório está disponível publicamente para avaliação:

🔗 https://github.com/FIAP-7/TECH-CHALLENGE

---

## 📚 Créditos

Projeto desenvolvido para o **Tech Challenge FIAP** como parte da entrega da fase.