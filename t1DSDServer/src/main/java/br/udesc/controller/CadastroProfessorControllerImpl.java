package br.udesc.controller;

import br.udesc.model.Professor;
import br.udesc.service.PessoaPersistence;
import br.udesc.service.PessoaPersistenceFactory;

public class CadastroProfessorControllerImpl implements CadastroPessoaController<Professor> {

    protected PessoaPersistence<Professor> professorPersistence;

    protected CadastroProfessorControllerImpl() {
        professorPersistence = PessoaPersistenceFactory.getPersistence(Professor.class);
    }

    @Override
    public void insert(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 6) {
            return;
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        String departamento = mensagem[5];

        Professor professor = new Professor(cpf, nome, endereco, departamento);
        professorPersistence.insert(professor);
    }

    @Override
    public String update(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 6) {
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        String departamento = mensagem[5];

        Professor professor = new Professor(cpf, nome, endereco, departamento);
        return professorPersistence.update(professor);
    }

    @Override
    public String get(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "CPF necessário";
        }
        String cpf = mensagem[2];
        return professorPersistence.get(cpf);
    }

    @Override
    public String delete(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        return professorPersistence.delete(cpf);
    }

    @Override
    public String list() {
        return professorPersistence.list();
    }

    protected String[] split(String msg) {
        return msg.split(";");
    }
}