package br.udesc.controller;

import br.udesc.model.Pessoa;
import br.udesc.service.Persistence;

public class CadastroController {

    private Persistence persistence;

    public CadastroController(){
        persistence = new Persistence();
    }

    // INSERT;cpf;nome;endereco
    public void insert(String msg){
        String[] mensagem = msg.split(";");
        if (mensagem.length < 4){
            return;
        }
        String cpf = mensagem[1];
        String nome = mensagem[2];
        String endereco = mensagem[3];
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        persistence.insert(pessoa);
    }

    // UPDATE;cpf;nome;endereco
    public String update(String msg){
        String[] mensagem = msg.split(";");
        if (mensagem.length < 4){
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[1];
        String nome = mensagem[2];
        String endereco = mensagem[3];
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        return persistence.update(pessoa);
    }

    // GET;cpf
    public String get(String msg){
        String[] mensagem = msg.split(";");
        if (mensagem.length == 1){
            return "CPF necessário";
        }
        String cpf = mensagem[1];
        return persistence.get(cpf);
    }

    // DELETE;cpf
    public String delete(String msg){
        String[] mensagem = msg.split(";");
        if (mensagem.length == 1){
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[1];
        return persistence.delete(cpf);
    }

    // LIST
    public String list(){
        return persistence.list();
    }

}
