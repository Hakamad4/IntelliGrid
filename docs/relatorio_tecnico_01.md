# Intelligrid: Relatório Técnico 01

Intelligrid é um sistema para monitoramento de consumo de energia de equipamentos. Nesta primeira fase, estabelecemos nossa fundação, definindo as três primeiras entidades da camada de domínio: Pessoa, Endereço e Eletrodoméstico. Em sequência, criamos a controladora e as classes de serviço para implantar um CRUD (*create, read, update, delete*) básico para as entidades. Por fim, anotamos as variáveis de classe para permitir a validação de dados e fizemos tratamentos das exceções por meio de um *Handler*.

## Objetivos

O principal objetivo deste relatório é discursar sobre a implementação da primeira fase do projeto.

Como objetivos específicos:

- Apresentar a estrutura e arquitetura;

- Discutir sobre escolhas realizadas e desafios encontrados.

## Desenvolvimento

### Tecnologias e Ferramentas

Configuramos um projeto utilizando a *stack* Java 17, *Spring Boot* 3 e a interface JPA (*Java Persistence API*)combinada com um banco de dados em memória H2 para persistência. Adicionamos também a dependência *Lombok*, para facilitar a criação de *Getters*, *Setters* e construtores.

Para versionamento estamos utilizando Git em combinação com a plataforma Github para orquestrar a colaboração dos esforços do time. Os membros do grupo estão trabalhando com suas IDEs preferência, seja IntelliJ, Eclipse ou VS Code. Dessa forma, foi implementado o arquivo `.gitignore` para não permitir a subida de configurações desses editores ao repositório do projeto.

Também consumimos uma API externa, a Viacep, para auxiliar na obtenção de dados de endereço no território brasileiro. Para realizar e processar a requisição utilizamos a interface *WebClient*, disponível no *Spring*.

Por fim, para gerenciar nossa biblioteca de requisições, realização de testes e documentação da API, utilizamos a ferramenta *Postman*.

### Arquitetura

padrões DDD (diretrizes) e REST (controller)

Responsabilidades: controller mais limpo, service com as regras de negócio

#### Camada de Domínio

Neste primeiro momento, foram implementadas três entidades do **subdomínio de suporte** relativas ao cadastro de informações no sistema: Pessoa, Endereço e Eletrodoméstico. Além das entidades, também temos classes auxiliares de repositórios, responsáveis pela persistência de dados.

<center>Fig. 1: Camada de Domínio</center>

![](imgs/dominio.png)

<center>Fonte: Produção dos autores, 2023.</center>
<br>

As seguintes entidades do subdomínio de suporte foram criadas:

- Pessoa: representa um usuário do sistema e gerencia informações como Nome, Data de Nascimento, Gênero, etc.

- Endereço: gerencia a informação de endereços cadastrados no sistema, como: Rua, Número, Bairro, Cidade e Estado.

- Eletrodoméstico: trata dos dados sobre eletrodomésticos cadastrados, por exemplo: Nome, Modelo, Potência, entre outros.

### Camadas de Controladora e Serviço

DTOs request e response

organizado como REST e CRUD, não persiste estado

Neste momento, todos os métodos de exclusão implementados efetivamente deletam os registros da base de dados, não foi implementada no presente momento nenhuma forma de *soft delete*.

## Discussões

Desafios encontrados

- Viacep e webclient

- Persistência: JPA e Banco de dados H2;

- Exceptions: a forma do Handler e a discussão de padronização de estrutura de código;

- Validação de dados: expansão das anotações para CEP e e-mail utilizando Regex;

- Lançamento de exceção quando não conseguir excluir.

### Persistência de dados

Neste primeiro momento, mesmo não sendo necessária persistência de dados, optamos pelo uso da interface JPA em conjunto com o banco de dados H2. Dessa forma, não foi necessária a criação de códigos e outras estruturas de dados em memória para testar o funcionamento de nossas funcionalidades enquanto em desenvolvimento.

O uso dessa estratégia também propicia as seguintes vantagens:

- Possibilidade de alteração transparente do banco de dados no futuro, uma vez que JPA é uma API;

- Mapeamento das entidades diretamente como tabelas do banco de dados;

- A nomenclatura padrão de métodos do JPA nos permitiu executar as operações no banco sem a necessidade de criação de queries SQL.

### Validações de dados

Com relação à validação dos dados de entrada provenientes das chamadas à nossa API, utilizamos as anotações de validação do *framework Jakarta*. Assim, nas classes responsáveis por abstrair os dados do corpo da requisição em objetos de DTO, foram incorporadas anotações quando necessário para evitar que objetos fossem nulos ou brancos, validações de datas como ou passadas e formato

Nessa estratégia, a primeira 

### Exclusão e informações

### Tratamento de Exceções

### Consumo de API externa


## Considerações Finais

