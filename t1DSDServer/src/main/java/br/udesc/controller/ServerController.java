package br.udesc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private static ServerController instance;

    private final int PORT = 1234;
    private CadastroPessoaController cadastroPessoaController;
    private CadastroProjetoPesquisaController cadastroProjetoPesquisaController;
    private ServerSocket server;


    private ServerController() throws IOException {
        this.cadastroPessoaController = CadastroPessoaController.getInstance();
        this.cadastroProjetoPesquisaController = CadastroProjetoPesquisaController.getInstance();
        openServer();
    }

    public static synchronized ServerController getInstance() throws IOException {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    private void openServer() throws IOException {
        server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"));
        server.setReuseAddress(true);
        System.out.println("Servidor rodando na porta: " + server.getLocalPort());
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
            // PESSOA;INSERT;cpf;nome;endereco etc. OU PROJETO;INSERT;nome;descricao;etc.
            String[] msg = dadosCmd.split(";");
            String cmd = msg[0];
            String retorno = new String("");
            switch (cmd) {
                case "PESSOA": retorno = pessoaMenu(msg); break; // sem retorno;
                case "PROJETO": retorno = projetoMenu(msg); break;
                default: retorno = "Comando inválido!"; break;
            }
            //no final fecha socket
            out.write(retorno.getBytes());
            con.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String pessoaMenu(String[] msg){
        if (msg.length == 1) return "Erro: Campos faltantes!";
        String retorno = new String("");
        String cmd = msg[1];
        String msgString = String.join(";", msg);
        switch (cmd) {
            case "INSERT": cadastroPessoaController.insert(msgString); break; // sem retorno;
            case "UPDATE": retorno = cadastroPessoaController.update(msgString); break;
            case "GET": retorno = cadastroPessoaController.get(msgString); break;
            case "DELETE": retorno = cadastroPessoaController.delete(msgString); break;
            case "LIST": retorno = cadastroPessoaController.list(); break;
            default: retorno = "Comando inválido!"; break;
        }
        return retorno;
    }

    private String projetoMenu(String[] msg){
        if (msg.length == 1) return "Erro: Campos faltantes!";
        String retorno = new String("");
        String cmd = msg[1];
        String msgString = String.join(";", msg);
        switch (cmd) {
            case "INSERT": cadastroProjetoPesquisaController.insert(msgString); break; // sem retorno;
            case "UPDATE": retorno = cadastroProjetoPesquisaController.update(msgString); break;
            case "GET": retorno = cadastroProjetoPesquisaController.get(msgString); break;
            case "DELETE": retorno = cadastroProjetoPesquisaController.delete(msgString); break;
            case "LIST": retorno = cadastroProjetoPesquisaController.list(); break;
            case "ADD": retorno = cadastroProjetoPesquisaController.addParticipante(msgString); break;
            case "REMOVE": retorno = cadastroProjetoPesquisaController.removeParticipante(msgString); break;
            default: retorno = "Comando inválido!"; break;
        }
        return retorno;
    }

}
