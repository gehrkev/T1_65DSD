package br.udesc.service;

import br.udesc.model.Pessoa;
import java.util.List;

/**
 * Interface para persistência de pessoas.
 * @param <T> Tipo de Pessoa a ser persistida
 */
public interface PessoaPersistence<T extends Pessoa> {

    void insert(T pessoa);

    String update(T pessoa);

    String get(String cpf);

    T getObject(String cpf);

    String delete(String cpf);

    String list();

    List<T> getPessoaList();
}