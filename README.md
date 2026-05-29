# 🌵 Raízes do Nordeste - Sistema de Pedidos

Sistema web desenvolvido em Java Spring Boot para gerenciamento de pedidos, estoque e filiais da rede fictícia **Raízes do Nordeste**.

---

# 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot
* Thymeleaf
* MySQL
* HTML5
* CSS3
* Maven
* Git / GitHub

---

# 🎯 Objetivo do Projeto

O sistema foi desenvolvido com foco em múltiplos canais de atendimento da rede:

* Aplicativo
* Totem de autoatendimento
* Atendimento balcão
* Retirada rápida (pick-up)

O objetivo é permitir uma experiência integrada para clientes e funcionários.

---

# 👥 Tipos de Usuários

## Cliente

* Visualiza cardápio
* Realiza pedidos
* Acompanha status do pedido

## Atendente

* Cria pedidos balcão
* Realiza pedidos para clientes presenciais

## Gerente

* Acompanha pedidos da filial
* Gerencia estoque da unidade

## Administrador

* Gerencia produtos
* Gerencia filiais
* Cadastra gerentes e atendentes

---

# 🍽️ Funcionalidades

* Login de usuários
* Controle de estoque por filial
* Cadastro de produtos
* Controle de pedidos
* Acompanhamento em tempo real
* Gerenciamento de filiais
* Sistema de pagamento
* Painel administrador
* Painel gerente
* Atendimento balcão

---

# 🏗️ Arquitetura

O projeto segue arquitetura MVC utilizando:

* Controllers
* Models
* Repositories
* Templates Thymeleaf

---

# 🗄️ Banco de Dados

O banco exportado está disponível em:

```text
/database/banco loja.sql
```

---

# ▶️ Como Executar

## 1. Importar banco

No MySQL Workbench:

* Server
* Data Import
* Selecionar:

```text
/database/banco loja.sql
```

---

## 2. Abrir Projeto

Importar o projeto Maven no Eclipse.

---

## 3. Executar

Executar a classe:

```text
BackendApplication.java
```

---

# 🌐 Acesso

```text
http://localhost:8080
```

---

# 🔐 Usuários de Teste

## Administrador

* Email: [admin@teste.com](mailto:admin@teste.com)
* Senha: 123

## Gerente

* Email: [gerente@teste.com](mailto:gerente@teste.com)
* Senha: 123

## Atendente

* Email: [atendente@teste.com](mailto:atendente@teste.com)
* Senha: 123

---

# 📚 Projeto Acadêmico

Projeto desenvolvido para aplicação prática de conceitos de:

* Engenharia de Software
* UML
* Arquitetura MVC
* Banco de Dados
* Desenvolvimento Web
* Controle de Requisitos
