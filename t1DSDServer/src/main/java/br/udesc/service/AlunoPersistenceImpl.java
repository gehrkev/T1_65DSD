package br.udesc.service;

import br.udesc.model.Aluno;
import br.udesc.model.Professor;

import java.util.ArrayList;
import java.util.List;

// TODO Implementar de acordo com o modelo em ProfessorPersistenceImpl
// TODO Só os métodos 'stub' foram inseridos, confirmar se não há mais em ProfessorPersistenceImpl
public class AlunoPersistenceImpl implements PessoaPersistence<Aluno>{

    private List<Aluno> alunoList;

    public AlunoPersistenceImpl() {
        alunoList = new ArrayList<>();
    }

    @Override
    public void insert(Aluno pessoa) {

    }

    @Override
    public String update(Aluno pessoa) {
        return "";
    }

    @Override
    public String get(String cpf) {
        return "";
    }

    @Override
    public Aluno getObject(String cpf) {
        return null;
    }

    @Override
    public String delete(String cpf) {
        return "";
    }

    @Override
    public String list() {
        return "";
    }

    @Override
    public List<Aluno> getPessoaList() {
        return alunoList;
    }

    @Override
    public String toString() {
        int tamanho = alunoList.isEmpty() ? 0 : alunoList.size();
        if (tamanho == 0) return "0";

        StringBuilder retorno = new StringBuilder(tamanho + "\n");
        for (Aluno a : alunoList) {
            retorno.append(a.toString()).append("\n");
        }
        return retorno.toString();
    }
}
