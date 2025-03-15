package br.udesc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private int PORT = 1234;
    private CadastroController cadastroController;
    private ServerSocket server;

    public ServerController(CadastroController cadastroController) throws IOException {
        this.cadastroController = cadastroController;
        openServer();
    }

    private void openServer() throws IOException {
        server = new ServerSocket(PORT);
        server.setReuseAddress(true);
        while(true) {
            serverListen();
        }
    }

    public void serverListen() {
        try (Socket con = this.server.accept();) {
            InputStream in = con.getInputStream();
            OutputStream out = con.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String dadosCmd = new String(buffer, 0, bytesRead);
            String[] msg = dadosCmd.split(";");
            String cmd = msg[0];
            String retorno = new String("");
            switch (cmd) {
                case "INSERT": cadastroController.insert(dadosCmd); break; // sem retorno;
                case "UPDATE": retorno = cadastroController.update(dadosCmd); break;
                case "GET": retorno = cadastroController.get(dadosCmd); break;
                case "DELETE": retorno = cadastroController.delete(dadosCmd); break;
                case "LIST": retorno = cadastroController.list(); break;
                default: retorno = "Comando inválido!"; break;
            }
            //no final fecha socket
            out.write(retorno.getBytes());
            con.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
