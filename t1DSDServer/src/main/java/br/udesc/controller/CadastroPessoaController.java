package br.udesc.controller;

import br.udesc.model.Pessoa;
import br.udesc.service.PessoaPersistence;

public class CadastroPessoaController {

    private static CadastroPessoaController instance;

    private PessoaPersistence pessoaPersistence;

    public CadastroPessoaController(){
        pessoaPersistence = PessoaPersistence.getInstance();
    }

    public static synchronized CadastroPessoaController getInstance(){
        if(instance == null){
            instance = new CadastroPessoaController();
        }
        return instance;
    }

    // PESSOA;INSERT;cpf;nome;endereco
    public void insert(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 5){
            return;
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        pessoaPersistence.insert(pessoa);
    }

    // PESSOA;UPDATE;cpf;nome;endereco
    public String update(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 5){
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        return pessoaPersistence.update(pessoa);
    }

    // PESSOA;GET;cpf
    public String get(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 3){
            return "CPF necessário";
        }
        String cpf = mensagem[2];
        return pessoaPersistence.get(cpf);
    }

    // PESSOA;DELETE;cpf
    public String delete(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 3){
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        return pessoaPersistence.delete(cpf);
    }

    // PESSOA;LIST
    public String list(){
        return pessoaPersistence.list();
    }

    private String[] split(String msg) {
        return msg.split(";");
    }

}
