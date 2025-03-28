package br.udesc.service;

import br.udesc.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaPersistenceImpl implements PessoaPersistence<Pessoa> {

    private List<Pessoa> pessoaList;

    PessoaPersistenceImpl() {
        pessoaList = new ArrayList<>();
    }

    @Override
    public void insert(Pessoa pessoa) {
        for (Pessoa p : pessoaList) {
            if (p.getCpf().equals(pessoa.getCpf())) {
                updateFields(p, pessoa);
                return;
            }
        }
        pessoaList.add(pessoa);
    }

    @Override
    public String update(Pessoa pessoa) {
        for (Pessoa p : pessoaList) {
            if (p.getCpf().equals(pessoa.getCpf())) {
                updateFields(p, pessoa);
                return "Pessoa atualizada com sucesso.";
            }
        }
        return "Pessoa não encontrada.";
    }

    protected void updateFields(Pessoa existente, Pessoa nova) {
        existente.setNome(nova.getNome());
        existente.setEndereco(nova.getEndereco());
    }

    @Override
    public String get(String cpf) {
        if (pessoaList.isEmpty()) return "Sem pessoas cadastradas";

        for (Pessoa pessoa : pessoaList) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa.toString();
            }
        }
        return "Pessoa não encontrada";
    }

    @Override
    public Pessoa getObject(String cpf) {
        if (pessoaList.isEmpty()) return new Pessoa();

        for (Pessoa pessoa : pessoaList) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return new Pessoa();
    }

    @Override
    public String delete(String cpf) {
        if (pessoaList.isEmpty()) return "Sem pessoas cadastradas";

        for (Pessoa p : pessoaList) {
            if (p.getCpf().equals(cpf)) {
                pessoaList.remove(p);
                return "Pessoa removida com sucesso.";
            }
        }

        return "Pessoa não encontrada";
    }

    @Override
    public String list() {
        return this.toString();
    }

    @Override
    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    @Override
    public String toString() {
        int tamanho = pessoaList.isEmpty() ? 0 : pessoaList.size();
        if (tamanho == 0) return "0";

        StringBuilder retorno = new StringBuilder(tamanho + "\n");
        for (Pessoa p : pessoaList) {
            retorno.append(p.toString()).append("\n");
        }
        return retorno.toString();
    }
}
