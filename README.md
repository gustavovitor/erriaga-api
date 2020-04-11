# Erriagá Backend
![Build and Test](https://github.com/gustavovitor/erriaga-api/workflows/Build/badge.svg?branch=master)

Backend feito em Java, utilizando Spring Boot 2.2.6.RELEASE e Java 11.

É uma aplicação simples utilizando o Maker SQL para criar a API no modelo Richardson.

Disponível no Docker Hub, [clique aqui...](https://hub.docker.com/r/gustavovitor/erriaga)

Para subir a imagem da API:
```docker run -dp 8080:8080 gustavovitor/erriaga:latest -name erriaga```

A API utiliza o contexto ```/api``` quando inicializar o container [clique aqui...](http://localhost:8080/api/swagger-ui.html?urls.primaryName=Auth)

# A API

### Grupo de Rotas: Auth
A API utiliza autenticação em duas etapas (OAuth2) do Spring Framework, o primeiro grupo da API seguindo a documentação
do Swagger é referente a autenticação, temos uma rota App User Resource que disponibiliza um **POST** para registrar um usuário.

A API conta também com um usuário padrão ```admin@admin.com``` e a senha ```1```.

Para utilizar as rotas de autenticação é necessário utilizar autenticação Basic. Disponível na interface do Swagger.

O usuário ```erriaga-api``` e a senha ```w+)Vjj!Y()Ed%j?bL;(%>.xa6&ZktM#/```. Isso é configurável no ```application.properties```.

Após registrar-se, obtenha seu Access Token no grupo de rotas ```token-endpoint```.

### Grupo de Rotas: Security
Dentro deste grupo de rotas você vai encontrar um recurso de Pessoas ```person-resource``` e um recurso de Token para logout ```token-resource```.

Na autenticação, cole seu token com o prefixo "Bearer" algo semelhante a ```Bearer access_token```.

<hr/>

##### Maker Project
As rotas são geradas através de uma biblioteca chamada [Maker Project](https://github.com/gustavovitor/maker).

Essa biblioteca gera rotas no padrão Richardson. (Falta apenas o último nível de implementação, o HATEOAS, está no meu roadmap para desenvolver dentro do Maker =])

Utilizo PUT nos métodos findAll e findAllPageable para facilitar a entrada de dados para especificação dentro do Maker.
