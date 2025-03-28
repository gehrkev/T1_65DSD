package br.udesc.controller;

import br.udesc.model.Pessoa;

/**
 * Interface para controllers de cadastro de pessoas.
 * @param <T> Tipo de Pessoa a ser gerenciada
 */
public interface CadastroPessoaController<T extends Pessoa> {

    void insert(String msg);

    String update(String msg);

    String get(String msg);

    String delete(String msg);

    String list();
}