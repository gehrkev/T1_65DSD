package br.udesc.controller;

import br.udesc.model.Pessoa;
import br.udesc.service.PessoaPersistence;
import br.udesc.service.PessoaPersistenceFactory;

public class CadastroPessoaControllerImpl implements CadastroPessoaController<Pessoa> {

    protected PessoaPersistence<Pessoa> pessoaPersistence;

    protected CadastroPessoaControllerImpl() {
        pessoaPersistence = PessoaPersistenceFactory.getPersistence(Pessoa.class);
    }

    // PESSOA;INSERT;cpf;nome;endereco
    @Override
    public void insert(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 5) {
            return;
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        Pessoa pessoa = criarPessoa(cpf, nome, endereco);
        pessoaPersistence.insert(pessoa);
    }

    // PESSOA;UPDATE;cpf;nome;endereco
    @Override
    public String update(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 5) {
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        Pessoa pessoa = criarPessoa(cpf, nome, endereco);
        return pessoaPersistence.update(pessoa);
    }

    // PESSOA;GET;cpf
    @Override
    public String get(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "CPF necessário";
        }
        String cpf = mensagem[2];
        return pessoaPersistence.get(cpf);
    }

    // PESSOA;DELETE;cpf
    @Override
    public String delete(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        return pessoaPersistence.delete(cpf);
    }

    // PESSOA;LIST
    @Override
    public String list() {
        return pessoaPersistence.list();
    }

    protected String[] split(String msg) {
        return msg.split(";");
    }

    protected Pessoa criarPessoa(String cpf, String nome, String endereco) {
        return new Pessoa(cpf, nome, endereco);
    }
}