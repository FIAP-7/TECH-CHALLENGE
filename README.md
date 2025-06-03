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

- Java 21+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- Lombok
- Postman (para testes de API)

---

## 📦 Estrutura do Projeto

- `config/`: Configurações gerais (segurança, CORS, etc.)
- `controller/`: Camada de exposição dos endpoints
- `domain/`: Entidades da aplicação
- `exception/`: Especificação dos erros e excessões do sistema
- `gateway/`: Abstração que prove a camada de dados independente da origem 
- `usecase/`: Lógica de negócio

---

## 🧪 Endpoints da API

| Método | Endpoint          | Descrição                        |
|--------|-------------------|----------------------------------|
| GET    | /usuarios/{id}     | Buscar usuário por ID         |
| PUT    | /usuarios/{id}     | Atualiza dados do usuário         |
| DELETE    | /usuarios/{id}     | Deletar usuário         |
| POST    | /usuarios/     | Criar novo usuário         |
| PATCH    | /usuario//{id}/senha     | Atualizar senha do usuário         |
| POST    | /login     | Autenticar usuário         |
---

## 🗃️ Campos do Usuário

- `id` (Long)
- `cpf` (String)
- `nome` (String)
- `email` (String)
- `login` (String)
- `senha` (String - criptografada)
- `dataUltimaAlteracao` (Date)
- `endereco` (Endereco)
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

Utilize a [collection do Postman](./documentacao/postaman-collections/Tech-Challenge.postman_collection.json) disponível no repositório para testar todos os endpoints da aplicação.

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
