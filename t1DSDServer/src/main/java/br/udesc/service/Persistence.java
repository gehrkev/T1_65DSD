package br.udesc.service;

import br.udesc.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Persistence {

    private List<Pessoa> pessoaList;

    public Persistence() {
        pessoaList = new ArrayList();
    }

    public void insert(Pessoa pessoa) {
        // Aqui poderíamos adicionar uma validação - se o cpf já existe ele faz o que?
        for (Pessoa p : pessoaList) {
            if (p.getCpf().equals(pessoa.getCpf())) {
                // Não faz nada?? Atualiza??
                // String a = update(pessoa);
                return;

            }
        }
        pessoaList.add(pessoa);
    }

    public String update(Pessoa pessoa) {
        for (Pessoa p : pessoaList) {
            if (p.getCpf().equals(pessoa.getCpf())) {
                p.setNome(pessoa.getNome());
                p.setEndereco(pessoa.getEndereco());
                return "Pessoa atualizada com sucesso.";
            }
        }
        return "Pessoa não encontrada.";
    }

    public String get(String cpf) {
        if (pessoaList.isEmpty()) return "Sem pessoas cadastradas";

        for (Pessoa pessoa : pessoaList) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa.toString();
            }
        }
        return "Pessoa não encontrada";
    }

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

    public String list() {
        return this.toString();
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
