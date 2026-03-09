💰 Gestão de Orçamento API

API REST desenvolvida em Java com Spring Boot para gerenciamento de rendas, despesas e saldo financeiro mensal.

Este projeto foi desenvolvido como prática de backend para consolidar conhecimentos em:

- Java
- Spring Boot
- Arquitetura em camadas
- JDBC
- APIs REST
- SQL

🚀 Funcionalidades

A API permite:

✔ cadastrar despesas
✔ cadastrar rendas
✔ buscar movimentações por data ou mês
✔ atualizar registros
✔ excluir registros
✔ calcular saldo total
✔ gerar resumo financeiro mensal

🏗 Arquitetura

O projeto segue arquitetura em camadas:

Controller → Service → Repository → Banco de Dados
Controller → recebe requisições HTTP
Service → contém regras de negócio
Repository → comunicação com banco via JDBC
Model → entidades do sistema

🛠 Tecnologias utilizadas

Java 17+
Spring Boot
JDBC
Maven
PostgreSQL
Git / GitHub

📡 Endpoints principais

Despesas
POST /despesas
GET /despesas
GET /despesas/{id}
GET /despesas/mes?mes=
PUT /despesas/attValor?id= &valor=
DELETE /despesas/excluir?id=

Rendas
POST /rendas
GET /rendas
GET /rendas/{id}
GET /rendas/mes?mes=
PUT /rendas/attValor?id= &valor=
DELETE /rendas/excluir?id=

Orçamento
GET /orcamento/saldoTotal
GET /orcamento/saldoMes?mes=
GET /orcamento/saldoMensal?mes=

📊 Exemplo de resposta

GET /orcamento/saldoMes?mes=3
{
  "mes": 3,
  "totalDespesas": 1200,
  "totalRendas": 3500,
  "saldo": 2300
}

📦 Como executar o projeto

1️⃣ Clonar o repositório
git clone https://github.com/seuusuario/gestor-orcamento.git

2️⃣ Entrar na pasta do projeto
cd gestor-orcamento

3️⃣ Rodar o projeto
mvn spring-boot:run

A API ficará disponível em:
http://localhost:8080

🎯 Objetivo do projeto

Este projeto foi desenvolvido para estudo de desenvolvimento backend com Java e Spring Boot, focando em:

- boas práticas de organização de código
- construção de APIs REST
- manipulação de banco de dados

🚧 Melhorias planejadas

Este projeto continuará evoluindo conforme avanço nos estudos de desenvolvimento backend. Algumas melhorias planejadas incluem:

    📊 Funcionalidades
    Criar relatórios financeiros mais completos
    Adicionar filtro por intervalo de datas
    Criar endpoint para resumo anual de rendas e despesas

    🗄 Banco de Dados
    Migrar a camada de persistência para Spring Data JPA

    📄 Documentação
    Adicionar documentação automática da API com Swagger
    Incluir exemplos de requisições utilizando Postman

    🧪 Testes
    Implementar testes unitários
    Adicionar testes de integração para os endpoints

    🐳 DevOps
    Criar Dockerfile para facilitar a execução da aplicação
    Criar docker-compose para subir aplicação + banco de dados rapidamente

👨‍💻 Autor

Luiz Henrique da Silva
Estudante de Desenvolvimento de Sistemas
