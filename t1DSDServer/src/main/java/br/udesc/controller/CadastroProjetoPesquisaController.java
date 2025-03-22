package br.udesc.controller;

import br.udesc.model.Pessoa;
import br.udesc.model.ProjetoPesquisa;
import br.udesc.service.PessoaPersistence;
import br.udesc.service.ProjetoPesquisaPersistence;

public class CadastroProjetoPesquisaController {

    private static CadastroProjetoPesquisaController instance;

    private ProjetoPesquisaPersistence projetoPesquisaPersistence;
    private PessoaPersistence pessoaPersistence;

    private CadastroProjetoPesquisaController(){
        projetoPesquisaPersistence = ProjetoPesquisaPersistence.getInstance();
        pessoaPersistence = PessoaPersistence.getInstance();
    }

    public static synchronized CadastroProjetoPesquisaController getInstance() {
        if (instance == null) {
            instance = new CadastroProjetoPesquisaController();
        }
        return instance;
    }

    // PROJETO;INSERT;nome;descricao;responsavel(cpf)
    public void insert(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 5){
            return;
        }
        String nomeGrupo = mensagem[2];
        String descricao = mensagem[3];
        String responsavel = mensagem[4]; // usar pessoaPersistence para recuperar responsavel
        Pessoa pessoaResponsavel = pessoaPersistence.getObject(responsavel);
        ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa(nomeGrupo, descricao, pessoaResponsavel);
        projetoPesquisaPersistence.insert(projetoPesquisa);
    }

    // PROJETO;UPDATE;nome;descricao;responsavel(cpf)
    public String update(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 5){
            return "Erro: Campos faltantes!";
        }
        String nomeGrupo = mensagem[2];
        String descricao = mensagem[3];
        String responsavel = mensagem[4];
        Pessoa pessoaResponsavel = pessoaPersistence.getObject(responsavel);
        ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa(nomeGrupo, descricao, pessoaResponsavel);
        return projetoPesquisaPersistence.update(projetoPesquisa);
    }

    // PROJETO;GET;codigo
    public String get(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 3){
            return "Código do projeto necessário";
        }
        String codigo = mensagem[2];
        return projetoPesquisaPersistence.get(Integer.parseInt(codigo));
    }

    // PROJETO;DELETE;codigo
    public String delete(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 3){
            return "Erro: Campos faltantes!";
        }
        String codigo = mensagem[2];
        return projetoPesquisaPersistence.delete(Integer.parseInt(codigo));
    }

    // PROJETO;LIST
    public String list(){
        return projetoPesquisaPersistence.list();
    }

    private String[] split(String msg) {
        return msg.split(";");
    }

    // PROJETO;ADD;codigo;cpf
    public String addParticipante(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 4){
            return "Erro: Campos faltantes!";
        }
        String codigo = mensagem[2];
        String cpf = mensagem[3];
        projetoPesquisaPersistence.addParticipante(codigo, cpf);

        return "";
    }

    // PROJETO;REMOVE;codigo;cpf
    public String removeParticipante(String msg){
        String[] mensagem = split(msg);
        if (mensagem.length < 4){
            return "Erro: Campos faltantes!";
        }
        String codigo = mensagem[2];
        String cpf = mensagem[3];
        projetoPesquisaPersistence.removeParticipante(codigo, cpf);
        return "";
    }
}
