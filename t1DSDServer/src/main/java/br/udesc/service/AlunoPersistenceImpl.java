package br.udesc.service;

import br.udesc.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoPersistenceImpl implements PessoaPersistence<Aluno> {

    private List<Aluno> alunoList;

    public AlunoPersistenceImpl() {
        alunoList = new ArrayList<>();
    }

    @Override
    public void insert(Aluno aluno) {
        for (Aluno a : alunoList) {
            if (a.getCpf().equals(aluno.getCpf())) {
                updateFields(a, aluno);
                return;
            }
        }
        alunoList.add(aluno);
    }

    @Override
    public String update(Aluno aluno) {
        for (Aluno a : alunoList) {
            if (a.getCpf().equals(aluno.getCpf())) {
                updateFields(a, aluno);
                return "Aluno atualizado com sucesso!";
            }
        }
        return "Aluno atualizado com sucesso!";
    }

    protected void updateFields(Aluno existente, Aluno novo) {
        existente.setNome(novo.getNome());
        existente.setEndereco(novo.getEndereco());
        existente.setMatricula(novo.getMatricula());
    }

    @Override
    public String get(String cpf) {
        if (alunoList.isEmpty()) return "Sem alunos cadastrados!";

        for (Aluno a : alunoList) {
            if (a.getCpf().equals(cpf)) {
                return a.toString();
            }
        }
        return "Aluno não encontrado!";
    }

    @Override
    public Aluno getObject(String cpf) {
        if (alunoList.isEmpty()) return new Aluno();

        for (Aluno a : alunoList) {
            if (a.getCpf().equals(cpf)) {
                return a;
            }
        }
        return new Aluno();
    }

    @Override
    public String delete(String cpf) {
        if (alunoList.isEmpty()) return "Sem alunos cadastrados!";

        for (Aluno a : alunoList) {
            if (a.getCpf().equals(cpf)) {
                alunoList.remove(a);
                return "Aluno removido com sucesso!";
            }
        }

        return "Aluno não encontrado!";
    }

    @Override
    public String list() {
        return this.toString();
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
