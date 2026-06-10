# Sistema de Gestão de Pedidos - Raízes do Nordeste

## Descrição

Este projeto foi desenvolvido como atividade prática da disciplina Projeto Multidisciplinar – Trilha Back-End.

A aplicação simula o sistema de gestão da rede de restaurantes "Raízes do Nordeste", permitindo o gerenciamento de clientes, produtos, estoque por filial, pedidos, fidelização e integração simulada de pagamentos.

O sistema foi desenvolvido utilizando Java, Spring Boot, Hibernate, Thymeleaf e MySQL, seguindo uma arquitetura organizada em camadas para facilitar manutenção, escalabilidade e evolução futura.

---

## Funcionalidades Implementadas

### Clientes

* Cadastro de clientes
* Login e autenticação
* Controle de acesso por perfil (ADMIN, GERENTE, ATENDENTE e CLIENTE)
* Programa de fidelidade

### Pedidos

* Criação de pedidos
* Carrinho de compras
* Controle de status
* Histórico de pedidos
* Registro do canal de origem do pedido

### Estoque

* Controle de estoque por filial
* Bloqueio de venda sem estoque
* Atualização automática de estoque

### Pagamentos

* Integração simulada (Mock)
* Aprovação de pagamento
* Recusa de pagamento
* Registro de transações

### Canais de Atendimento

* WEB
* APP
* TOTEM
* BALCÃO
* PICKUP

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Thymeleaf
* MySQL
* Maven
* HTML5
* CSS3

---

## Arquitetura do Projeto

### Domain

Responsável pelas entidades e regras de negócio.

Exemplos:

* Cliente
* Pedido
* Produto
* ItemPedido
* Estoque
* Filial
* Pagamento

### Application

Responsável pelos fluxos de negócio.

Exemplos:

* PagamentoService
* Fidelização
* Atualização de status

### Infrastructure

Responsável pela persistência dos dados.

Exemplos:

* Repositories JPA
* Configurações Hibernate
* Banco MySQL

### API / Controllers

Responsável pela exposição das funcionalidades do sistema.

Exemplos:

* PedidoApiController
* ProdutoApiController
* EstoqueApiController
* PagamentoApiController
* LoginController
* AdminController

---

## Segurança

O sistema utiliza criptografia BCrypt para armazenamento seguro das senhas dos usuários.

Funcionalidades implementadas:

* Hash de senha utilizando BCrypt
* Autenticação por login
* Controle de acesso por perfis (roles)
* Proteção contra armazenamento de senhas em texto puro

---

## Regras de Negócio

* Cada filial possui estoque próprio.
* Produtos podem estar disponíveis apenas em determinadas filiais.
* Não é permitido vender produtos sem estoque.
* Todo pedido possui um canal de origem.
* O programa de fidelidade permite acumular pontos.
* Pagamentos são processados por integração simulada (Mock).
* Pagamentos aprovados colocam o pedido em EM PREPARO.
* Pagamentos recusados cancelam o pedido.

---

## Usuários de Teste

### Administrador

Email: [admin@loja.com](mailto:admin@loja.com)

Senha: 123456

### Gerente

Email: [gerente@lojaCentro.com](mailto:gerente@lojaCentro.com)

Senha: 123456

### Atendente

Email: [atendente@lojaCentro.com](mailto:atendente@lojaCentro.com)

Senha: 123456

### Cliente

Email: [cliente@loja.com](mailto:cliente@loja.com)

Senha: 123456

---

## Testes Realizados

| Caso de Teste       | Resultado |
| ------------------- | --------- |
| Login válido        | Sucesso   |
| Login inválido      | Sucesso   |
| Produto sem estoque | Sucesso   |
| Pagamento aprovado  | Sucesso   |
| Pagamento recusado  | Sucesso   |

Os testes realizados demonstraram o correto funcionamento das funcionalidades críticas do sistema, incluindo autenticação, controle de estoque e processamento de pagamentos.

---

## Banco de Dados

Banco utilizado:

MySQL

Principais tabelas:

* cliente
* pedido
* item_pedido
* produto
* estoque
* filial
* pagamento

---

## Como Executar

1. Clonar o repositório

git clone URL_DO_REPOSITORIO

2. Criar o banco de dados

## Configuração do Banco de Dados

O projeto utiliza MySQL como banco de dados.

### Restaurando o banco

O arquivo de backup encontra-se em:

```text
database/banco_loja.sql
```

Para restaurar o banco:

. Criar o banco de dados:

```sql
CREATE DATABASE loja;
```

. Abrir o MySQL Workbench.

. Selecionar:

```text
Server → Data Import
```

. Escolher:

```text
Import from Self-Contained File
```

. Selecionar o arquivo:

```text
database/banco_loja.sql
```

. Escolher o schema:

```text
loja
```

. Executar:

```text
Start Import
```

### Configuração da aplicação

Verificar o arquivo:

```text
src/main/resources/application.properties
```

e ajustar as credenciais do MySQL, caso necessário:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/loja
spring.datasource.username=root
spring.datasource.password=sua_senha
```

Após a importação do banco e configuração da conexão, a aplicação estará pronta para execução.

. Observação

O banco de dados enviado já contém dados de demonstração, incluindo usuários, produtos, filiais e registros necessários para validação das funcionalidades implementadas.



3. Configurar o arquivo application.properties

4. Executar a aplicação utilizando Maven ou pela classe principal Spring Boot.

5. Acessar:

http://localhost:8080

---
## Usuários de Teste

Para facilitar a validação das funcionalidades do sistema, foram disponibilizados usuários com diferentes perfis de acesso.

| Perfil    | Funcionalidade                                   |
| --------- | -----------------------------------------------  |
| ADMIN     | Administração geral do sistema                   |
| GERENTE   | Gestão de estoque, clientes e operação           |
| ATENDENTE | Realização e acompanhamento de pedidos no balcão |
| CLIENTE   | Realização de pedidos e fidelização              |

Exemplo de credenciais:

| Perfil           | E-mail                                                | Senha  |
| ---------------- | ----------------------------------------------------- | ------ |
| ADMIN            | [admin@loja.com](mailto:admin@loja.com)               | 123456 |
| GERENTE-CENTRO   | [gerente@lojaCentro.com](mailto:gerente@loja.com)     | 123456 |
| ATENDENTE-CENTRO | [atendente@lojaCentro.com](mailto:atendente@loja.com) | 123456 |
| CLIENTE          | [cliente@loja.com](mailto:cliente@loja.com)           | 123456 |

Obs.: As credenciais podem ser alteradas diretamente no banco de dados para fins de teste.


## Autor

Vanderlei Marcelo Calza RU:4207205

Projeto desenvolvido para fins acadêmicos na disciplina Projeto Multidisciplinar – Trilha Back-End.
