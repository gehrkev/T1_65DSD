package br.udesc;

import br.udesc.controller.ServerController;
import br.udesc.controller.CadastroController;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        CadastroController cadastroController = new CadastroController();
        ServerController controle = new ServerController(cadastroController);

    }
}