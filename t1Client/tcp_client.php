<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $mensagem = $_POST["mensagem"];
    $host = $_POST["ip"];
    $port = 1234;

    $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    if (!$socket) {
        echo "ERRO: Erro ao criar socket.";
        exit;
    }

    $result = @socket_connect($socket, $host, $port);
    if (!$result) {
        echo "ERRO: Falha na conexão. O IP pode estar incorreto.";
        exit;
    }

    socket_write($socket, $mensagem, strlen($mensagem));
    $resposta = socket_read($socket, 1024);
    socket_close($socket);

    echo trim($resposta);
}
