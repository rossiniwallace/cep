# Sistema de Entrega com Pesquisa de CEP

Este projeto é uma API REST para gerenciar entregas, incluindo a busca de CEPs em uma API externa mockada, criação de códigos de rastreio e registro de logs no banco de dados. O sistema também utiliza autenticação via Keycloak e está configurado para ser implantado em ambientes Docker e AWS.

---

## 🚀 Funcionalidades

- **Busca de CEP:** Consulta informações de endereço a partir de uma API externa mockada (ex.: WireMock).
- **Criação de Entregas:** Geração de entregas com código de rastreamento único.
- **Registro de Logs:** Armazena as consultas de CEP no banco de dados com horário e resposta.
- **Autenticação:** Integração com Keycloak para controle de acesso.
- **Implantação:** Compatível com Docker e preparado para rodar na AWS.

---

## 🛠️ Tecnologias Utilizadas

- **Java** com **Spring Boot**
- **PostgreSQL** como banco de dados
- **Keycloak** para autenticação e autorização
- **Docker** para containerização
- **WireMock** ou **Mockoon** como API externa mockada
- **AWS ECS**, **ECR**, **RDS**, **CloudWatch** e **ALB** para hospedagem na nuvem

---

## 📦 Estrutura do Projeto
```
src/
├── main/
│   ├── java/com/service/cep/
│   │   ├── config/
│   │   │   ├── JWTConverter         # Classe de manipulação de JWT
│   │   │   └── SecurityConfig       # Configurações de segurança
│   │   ├── controller/
│   │   │   ├── advice/              # Controladores relacionados a conselhos
│   │   │   │   ├── AuthController   # Autenticação
│   │   │   │   ├── DeliveryController # Entregas
│   │   │   │   └── ExternalQueryLogController # Logs de consultas externas
│   │   ├── domain/
│   │   │   ├── enums/               # Definições de enums
│   │   │   │   ├── Delivery         # Enum relacionado a entregas
│   │   │   │   └── ExternalQueryLog # Enum para logs externos
│   │   ├── dto/
│   │   │   ├── delivery/            # Objetos de transferência relacionados a entregas
│   │   │   └── webservice/          # DTOs para integração com webservices
│   │   ├── exception/               # Tratamento de exceções
│   │   ├── mapper/                  # Classes para mapeamento entre entidades e DTOs
│   │   ├── repository/              # Repositórios JPA
│   │   ├── service/                 # Regras de negócio
│   │   └── utils/                   # Utilitários gerais
├── wiremock/                      # Configuração do Wiremock para mock de APIs externas
│   ├── __files/                   # Arquivos simulados (respostas mockadas)
│   └── mappings/                  # Mapeamentos das rotas mockadas
├── docker-compose.yml             # Configuração do Docker Compose

```

## ⚙️ Configuração e Execução

### Pré-requisitos

- **Java 17+**
- **Docker**
- **PostgreSQL** (se rodar localmente)
- **Keycloak** (configurado em container)

### Passo a Passo

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio