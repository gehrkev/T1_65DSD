# Servidor TCP Java

## Requisitos

1. JDK 21 instalado
2. Maven (opcional, para compilar o projeto)

## Compilação

### Maven
Caso você não tenha o projeto compilado (arquivo `t1DSDServer-1.0-SNAPSHOT.jar`), execute o comando a seguir no diretório `T1_65DSD/t1DSDServer` do projeto:
```
mvn clean package
```

###
O JAR compilado estará disponível na pasta `T1_65DSD/t1DSDServer/target/`

## Execução

Execute o JAR com o comando a seguir no diretório `T1_65DSD/t1DSDServer` do projeto:
```
java -jar target/t1DSDServer-1.0-SNAPSHOT.jar
```

## Notas
- O servidor escuta conexões na porta 1234
- Certifique-se de que nenhum firewall está bloqueando esta porta