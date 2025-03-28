package br.udesc.service;

import br.udesc.controller.CadastroProfessorController;
import br.udesc.model.Pessoa;
import br.udesc.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorPersistence implements Persistence{
    private static ProfessorPersistence instance;

    private List<Professor> professorList;

    private ProfessorPersistence() {
        professorList = new ArrayList();
    }

    public Persistence getInstance() {
        if (instance == null) {
            instance = new ProfessorPersistence();
        }
        return instance;
    }

    public void insert(Professor professor) {
        for (Professor p : professorList) {
            if (p.getCpf().equals(professor.getCpf())) {
                // Não faz nada?? Atualiza??
                // String a = update(pessoa);
                return;

            }
        }
        professorList.add(professor);
    }

    public String update(Professor professor) {
        for (Professor p : professorList) {
            if (p.getCpf().equals(professor.getCpf())) {
                p.setNome(professor.getNome());
                p.setEndereco(professor.getEndereco());
                return "Professor atualizado com sucesso.";
            }
        }
        return "Professor não encontrado.";
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
        return "";
    }

    public String list() {
        return "";
    }
}
