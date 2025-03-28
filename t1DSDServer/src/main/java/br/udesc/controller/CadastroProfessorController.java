package br.udesc.controller;

import br.udesc.model.Professor;
import br.udesc.service.ProfessorPersistence;

public class CadastroProfessorController {
    private static CadastroProfessorController instance;

    private ProfessorPersistence professorPersistence;

    public CadastroProfessorController(){
        professorPersistence = ProfessorPersistence.getInstance();
    }

    public static synchronized CadastroProfessorController getInstance(){
        if(instance == null){
            instance = new CadastroProfessorController();
        }
        return instance;
    }

    // PROFESSOR;INSERT;cpf;nome;endereco;departamento
    public void insert(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 6){
            return;
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        String departamento = mensagem[5];
        Professor professor = new Professor(cpf, nome, endereco, departamento);
        professorPersistence.insert(professor);
    }

    // PROFESSOR;UPDATE;cpf;nome;endereco;departamento
    public String update(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 6){
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        String nome = mensagem[3];
        String endereco = mensagem[4];
        String departamento = mensagem[5];
        Professor professor = new Professor(cpf, nome, endereco, departamento);
        return professorPersistence.update(professor);
    }

    // PROFESSOR;GET;cpf
    public String get(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 3){
            return "CPF necessário";
        }
        String cpf = mensagem[2];
        return professorPersistence.get(cpf);
    }

    // PROFESSOR;DELETE;cpf
    public String delete(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 3){
            return "Erro: campos faltantes!";
        }
        String cpf = mensagem[2];
        return professorPersistence.delete(cpf);
    }

    // PROFESSOR;LIST
    public String list(){
        return professorPersistence.list();
    }

    private String[] split(String msg) {
        return msg.split(";");
    }
}
