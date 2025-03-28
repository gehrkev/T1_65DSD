package br.udesc.service;

import br.udesc.model.Pessoa;
import br.udesc.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorPersistenceImpl implements PessoaPersistence<Professor> {

    private List<Professor> professorList;

    public ProfessorPersistenceImpl() {
        professorList = new ArrayList<>();
    }

    @Override
    public void insert(Professor professor) {
        for (Professor p : professorList) {
            if (p.getCpf().equals(professor.getCpf())) {
                updateFields(p, professor);
                return;

            }
        }
        professorList.add(professor);
    }

    @Override
    public String update(Professor professor) {
        for (Professor p : professorList) {
            if (p.getCpf().equals(professor.getCpf())) {
                updateFields(p, professor);
                return "Professor atualizado com sucesso.";
            }
        }
        return "Professor não encontrado.";
    }

    protected void updateFields(Professor existente, Professor novo) {
        existente.setNome(novo.getNome());
        existente.setEndereco(novo.getEndereco());
        existente.setDepartamento(novo.getDepartamento());
    }

    @Override
    public String get(String cpf) {
        if (professorList.isEmpty()) return "Sem professores cadastrados";

        for (Professor p : professorList) {
            if (p.getCpf().equals(cpf)) {
                return p.toString();
            }
        }
        return "Professor não encontrado";
    }

    @Override
    public Professor getObject(String cpf) {
        if (professorList.isEmpty()) return new Professor();

        for (Professor p : professorList) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return new Professor();
    }

    @Override
    public String delete(String cpf) {
        if (professorList.isEmpty()) return "Sem professores cadastrados";

        for (Professor p : professorList) {
            if (p.getCpf().equals(cpf)) {
                professorList.remove(p);
                return "Professor removido com sucesso.";
            }
        }

        return "Professor não encontrado";
    }

    @Override
    public String list() {
        return this.toString();
    }

    @Override
    public List<Professor> getPessoaList() {
        return professorList;
    }

    @Override
    public String toString() {
        int tamanho = professorList.isEmpty() ? 0 : professorList.size();
        if (tamanho == 0) return "0";

        StringBuilder retorno = new StringBuilder(tamanho + "\n");
        for (Professor p : professorList) {
            retorno.append(p.toString()).append("\n");
        }
        return retorno.toString();
    }
}
