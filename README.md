# Gestão de livraria virtual

Spring boot REST API para gestão de uma livraria virtual

API implementada como parte da disciplina de Arquitetura de Backend e Microsserviços do curso de pós-graduação em Arquitetura de Software Distribuído da PUC Minas.

## Autor

* Jonas Rocha Castanheira
* Matrícula: 109997

## Justificativa de conformidade às 11 regras de APIs
### 1 - Orientação a recursos
A documentação da API no Swagger deixa claro o mapeamento das regras de negócio refletido nos grupos de recursos (livros, carrinhos de compras e pedidos de compras) aos quais operações podem ser realizadas. O tipo de operação a ser realizada com um recurso fica a cargo do método HTTP do endpoint.

### 2 - Padronização da API
A API segue padrões tanto em suas rotas, orientadas a coleções e, por vezes, IDs de itens em coleções, como também está padronizada em sua documentação. O palavreado e a formatação das descrições de recursos, métodos e parâmetros seguem as mesmas definições prévias.

### 3 - Evitar APIs anêmicas
O formato da API e os tipos de recursos e operações sobre recursos que a API disponibiliza mostram que não apenas operações CRUD foram implementadas, mas que as regras de negócio da livraria virtual foram devidamente convertidas em operações.

### 3 - Criação de APIs simples
O tamanho, os nomes e formatos de rotas, métodos e parâmetros foram mantidos simples e padronizados. Apenas no caso de adição e remoção de livros dos carrinhos de compra, a URI de um endpoint se estende mais do que "coleção/item/coleção".

### 5 - Atualização em lote
Por questões de praticidade e de não haver ligação com formas de persistência de dados, essa regra foi desconsiderada.

### 6 - Tratamento de datas
Todas as datas expostas pela API receberam formatação em conformidade com o padrão ISO 8601, como fica demonstrado pelas datas de criação de quaisquer recursos da API (livros, carrinhos de compras ou pedidos de compras).
 
### 7 - Documentação da API
Toda a documentação da API está disponível através do Swagger.
 
### 8 - Protocolo HTTPS
Essa regra foi desconsiderada por questões de praticidade e viabilidade, e pelo fato de o projeto ser um mock a ser executado em ambiente local.
 
### 9 - Versionamento de API
Como as URIs dos métodos demonstram, a API está versionada corretamente.
 
### 10 - Paginação
Essa regra foi desconsiderada por questões de praticidade e viabilidade.
 
### 11 - Códigos de retorno HTTP corretos
A API retorna corretamente os códigos HTTP esperados: 200 quando um recurso é buscado com sucesso e 201 quando um recurso é criado com sucesso. Os demais códigos da sequência 2xx não foram necessários pela simplicidade da API. Os erros retornados entram na casa 4xx de códigos HTTP, com o código 404 sendo gerado para o caso de recurso não encontrado, 400 para uma requisição com dados inválidos passados pelo usuário da API e 500 caso ocorra erro interno na API.

