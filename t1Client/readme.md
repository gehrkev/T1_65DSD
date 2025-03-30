# Cliente TCP PHP

## Requisitos

1. PHP instalado (versão 7.0 ou superior)
2. Extensão de sockets habilitada no PHP

## Configuração

1. **Habilitar a extensão sockets no php.ini:**
    - Localize o arquivo `php.ini` (geralmente em `C:\php\php.ini` no Windows)
    - Descomente a linha `extension=sockets` (remova o `;` do início)
    - Salve o arquivo

## Execução

1. Navegue até o diretório do cliente no terminal/prompt de comando:
    ```
    cd T1_65DSD/t1Client
    ```
2. Execute o servidor PHP:
   ```
   php -S localhost:8000
   ```
3. Acesse o cliente no navegador:
   ```
   http://localhost:8000
   ```

## Notas
- Certifique-se de que o servidor Java está em execução na porta 1234
- O servidor PHP e Java podem estar em máquinas diferentes