package br.udesc.service;

import br.udesc.model.Pessoa;

import java.util.List;


public interface Persistence {
    Persistence instance = null;

    List<Pessoa> pessoaList = List.of();

    Persistence getInstance();

    void insert(Object pessoa);

    String update(Pessoa pessoa);

    String get(String cpf);

    String delete(String cpf);

    String list();

}
