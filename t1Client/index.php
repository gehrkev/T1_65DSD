<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>65DSD</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container">
        <h1>Cadastro de Projetos de Pesquisa</h1>

        <label for="tipo">Selecione o Tipo:</label>
        <select id="tipo" onchange="atualizarOperacoes()">
            <option value="PESSOA">Pessoa</option>
            <option value="ALUNO">Aluno</option>
            <option value="PROFESSOR">Professor</option>
            <option value="PROJETO">Projeto</option>
        </select>

        <label for="operacao">Operação:</label>
        <select id="operacao"></select>

        <input type="text" id="dados" placeholder="........">
        <button onclick="enviarRequisicao()">Enviar</button>

        <h2>Resposta do Servidor:</h2>
        <p id="resposta"></p>
    </div>

    <div id="modal">
        <div class="modal-content">
            <h2>Informe o IP do Servidor</h2>
            <input type="text" id="ipInput" placeholder="127.0.0.1">
            <button onclick="testarConexao()">Conectar</button>
            <p id="errorMessage" class="error-message"></p>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            let ipServidor = localStorage.getItem("serverIP");
            if (!ipServidor) {
                document.getElementById("modal").style.display = "flex";
            }
            atualizarOperacoes();
        });

        function testarConexao() {
            let ipServidor = document.getElementById("ipInput").value.trim();
            if (!ipServidor) {
                alert("Por favor, informe um IP válido.");
                return;
            }

            fetch("tcp_client.php", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: "mensagem=PESSOA;LIST&ip=" + encodeURIComponent(ipServidor)
                })
                .then(response => response.text())
                .then(data => {
                    if (data.startsWith("ERRO:")) {
                        let errorMessage = document.getElementById("errorMessage");
                        errorMessage.innerText = "Erro ao conectar. Tente outro IP.";
                        errorMessage.style.display = "block";
                        setTimeout(() => {
                            errorMessage.style.display = "none";
                        }, 3000);
                    } else {
                        localStorage.setItem("serverIP", ipServidor);
                        document.getElementById("modal").style.display = "none";
                    }
                })
                .catch(error => {
                    let errorMessage = document.getElementById("errorMessage");
                    errorMessage.innerText = "Falha na conexão. Verifique o IP.";
                    errorMessage.style.display = "block";
                    setTimeout(() => {
                        errorMessage.style.display = "none";
                    }, 3000);
                });
        }

        function enviarRequisicao() {
            let ipServidor = localStorage.getItem("serverIP");
            if (!ipServidor) {
                document.getElementById("modal").style.display = "flex";
                return;
            }

            let tipo = document.getElementById("tipo").value;
            let operacao = document.getElementById("operacao").value;
            let dados = document.getElementById("dados").value.trim();
            let mensagem = `${tipo};${operacao}`;
            if (dados) mensagem += `;${dados}`;

            fetch("tcp_client.php", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: "mensagem=" + encodeURIComponent(mensagem) + "&ip=" + encodeURIComponent(ipServidor)
                })
                .then(response => response.text())
                .then(data => {
                    document.getElementById("resposta").innerText = data;
                })
                .catch(error => {
                    console.error("Erro:", error);
                });
        }

        function atualizarOperacoes() {
            let tipo = document.getElementById("tipo").value;
            let operacaoSelect = document.getElementById("operacao");
            operacaoSelect.innerHTML = `
                <option value="INSERT">Inserir</option>
                <option value="UPDATE">Atualizar</option>
                <option value="GET">Buscar</option>
                <option value="DELETE">Apagar</option>
                <option value="LIST">Listar</option>
            `;
            if (tipo === "PROJETO") {
                operacaoSelect.innerHTML += `
                    <option value="ADD">Adicionar Participante</option>
                    <option value="REMOVE">Remover Participante</option>
                `;
            }
        }
    </script>
</body>

</html>