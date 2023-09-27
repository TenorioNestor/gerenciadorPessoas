# Projeto de Gerenciamento de Pessoas

## Bem vindo


## Criar uma pessoa

* Endpoint:
* POST: http://localhost:8080/api/cadastro

```javascript
{
    "nome": "Nestor Teste", 
    "dataNascimento": "2023-02-02"
}
```
## Editar uma pessoa

* Endpoint:
* PUT: http://localhost:8080/api/cadastro/{id}

```javascript
{
    "nome": "Nestor Teste Tenorio", 
    "dataNascimento": "2023-02-02"
}
```
## Consultar uma pessoa

* Endpoint:
* GET: http://localhost:8080/api/cadastro/{id}

## Consultar uma lista de pessoas

* Endpoint:
* GET: http://localhost:8080/api/cadastro

## Criar endereço para uma pessoa

* Endpoint:
* POST: http://localhost:8080/api/cadastro
```javascript
 {
    "nome":"Nestor Teste",
        "dataNascimento":"2023-02-02",
        "cadastroEndereco":{
        "logradouro":"Rua Lateral",
            "cep":"88350688",
            "numero": "80",
            "cidade":"Brusque",
            "principal":true
    }
} 
```
## Consultar uma lista de endereços

* Endpoint:
* GET: http://localhost:8080/api/enderecos

### DB utilizado para a execução do projeto foi o H2

### Para a realização dos testes foi utilizado jUnit e o módulo Test do Spring
