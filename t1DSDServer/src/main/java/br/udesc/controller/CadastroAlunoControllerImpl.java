package br.udesc.controller;

import br.udesc.model.Aluno;
import br.udesc.model.Professor;
import br.udesc.service.PessoaPersistence;
import br.udesc.service.PessoaPersistenceFactory;

// TODO Implementar de acordo com o modelo em CadastroProfessorControllerImpl
// TODO Só os métodos 'stub' foram inseridos, confirmar se não há mais em CadastroProfessorControllerImpl
public class CadastroAlunoControllerImpl implements CadastroPessoaController<Aluno> {

    protected PessoaPersistence<Aluno> alunoPersistence;

    protected CadastroAlunoControllerImpl() {
        alunoPersistence = PessoaPersistenceFactory.getPersistence(Aluno.class);
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
        String matricula = mensagem[5];

        Aluno aluno = new Aluno(cpf, nome, endereco, matricula);
        alunoPersistence.insert(aluno);
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
        String matricula = mensagem[5];

        Aluno aluno = new Aluno(cpf, nome, endereco, matricula);
        return alunoPersistence.update(aluno);
    }

    @Override
    public String get(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "CPF necessário";
        }
        String cpf = mensagem[2];
        return alunoPersistence.get(cpf);
    }

    @Override
    public String delete(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        return alunoPersistence.delete(cpf);
    }

    @Override
    public String list() {
        return alunoPersistence.list();
    }

    protected String[] split(String msg) {
        return msg.split(";");
    }
}
