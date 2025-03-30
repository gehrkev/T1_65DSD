# Sistema de Cadastro de Projetos de Pesquisa

Este projeto consiste em um sistema cliente-servidor para cadastro e gerenciamento de projetos de pesquisa, utilizando comunicação TCP/IP.

## Estrutura do Projeto

```
.
├── t1Client          # Cliente PHP
├── t1DSDServer       # Servidor Java
└── testClient        # Cliente Java de teste
```

## Requisitos

- JDK 21
- PHP 7.0+ com extensão de sockets habilitada
- Maven (opcional)

## Instalação

Clone o repositório:
```
git clone https://github.com/gehrkev/T1_65DSD.git
cd T1_65DSD
```

## Execução

### Servidor Java

Consulte [t1DSDServer/readme.md](t1DSDServer/readme.md) para instruções detalhadas sobre como compilar e executar o servidor.

Resumo:
```
cd t1DSDServer
java -jar target/t1DSDServer-1.0-SNAPSHOT.jar
```

### Cliente PHP

Consulte [t1Client/readme.md](t1Client/readme.md) para instruções detalhadas sobre como configurar e executar o cliente web.

Resumo:
```
cd t1Client
php -S localhost:8000
```
Depois acesse http://localhost:8000 no navegador.
Em caso de erro de conexão, consulte o [readme.md](t1Client/readme.md)

## Clientes Disponíveis

- **Cliente Web (PHP)**: Interface gráfica para acesso via navegador
- **Cliente de Teste (Java)**: Cliente CLI alternativo em Java para testes