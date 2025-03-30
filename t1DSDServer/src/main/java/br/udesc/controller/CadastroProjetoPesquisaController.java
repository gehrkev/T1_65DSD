package br.udesc.controller;

import br.udesc.model.Pessoa;
import br.udesc.model.Professor;
import br.udesc.model.ProjetoPesquisa;
import br.udesc.service.*;

public class CadastroProjetoPesquisaController {

    private static CadastroProjetoPesquisaController instance;

    private ProjetoPesquisaPersistence projetoPesquisaPersistence;
    private PessoaPersistence<Professor> professorPersistence;

    private CadastroProjetoPesquisaController(){
        projetoPesquisaPersistence = ProjetoPesquisaPersistence.getInstance();
        professorPersistence = PessoaPersistenceFactory.getPersistence(Professor.class);
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
        String responsavelCpf = mensagem[4];
        Professor responsavel = professorPersistence.getObject(responsavelCpf);

        if (responsavel == null || responsavel.getCpf() == null || responsavel.getCpf().isEmpty() || responsavel.getCpf().equals("-1")) {
            return; // Não encontrou professor válido
        }

        ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa(nomeGrupo, descricao, responsavel);
        projetoPesquisaPersistence.insert(projetoPesquisa);
    }

    // PROJETO;UPDATE;codigo;nome;descricao;responsavel(cpf)
    public String update(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 6) {
            return "Erro: Campos faltantes!";
        }

        try {
            Integer codigo = Integer.parseInt(mensagem[2]);
            String nomeGrupo = mensagem[3];
            String descricao = mensagem[4];
            String responsavelCpf = mensagem[5];

            // Busca o professor responsável usando a persistência de professores
            Professor responsavel = professorPersistence.getObject(responsavelCpf);

            // Verifica se encontrou um professor válido
            if (responsavel == null || responsavel.getCpf() == null || responsavel.getCpf().isEmpty() || responsavel.getCpf().equals("-1")) {
                return "Professor responsável não encontrado";
            }

            ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa(nomeGrupo, descricao, responsavel);
            projetoPesquisa.setCodigo(codigo);
            return projetoPesquisaPersistence.update(projetoPesquisa);
        } catch (NumberFormatException e) {
            return "Código do projeto inválido";
        }
    }

    // PROJETO;GET;codigo
    public String get(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "Código do projeto necessário";
        }
        try {
            String codigo = mensagem[2];
            return projetoPesquisaPersistence.get(Integer.parseInt(codigo));
        } catch (NumberFormatException e) {
            return "Código do projeto inválido";
        }
    }

    // PROJETO;DELETE;codigo
    public String delete(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 3) {
            return "Erro: Campos faltantes!";
        }
        try {
            String codigo = mensagem[2];
            return projetoPesquisaPersistence.delete(Integer.parseInt(codigo));
        } catch (NumberFormatException e) {
            return "Código do projeto inválido";
        }
    }

    // PROJETO;LIST
    public String list(){
        return projetoPesquisaPersistence.list();
    }

    private String[] split(String msg) {
        return msg.split(";");
    }

    // PROJETO;ADD;codigo;cpf
    public String addParticipante(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 4) {
            return "Erro: Campos faltantes!";
        }
        try {
            String codigo = mensagem[2];
            String cpf = mensagem[3];
            projetoPesquisaPersistence.addParticipante(codigo, cpf);
            return "Participante adicionado com sucesso";
        } catch (NumberFormatException e) {
            return "Código do projeto inválido";
        } catch (IllegalStateException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado: " + e.getMessage();
        }
    }


    // PROJETO;REMOVE;codigo;cpf
    public String removeParticipante(String msg) {
        String[] mensagem = split(msg);
        if (mensagem.length < 4) {
            return "Erro: Campos faltantes!";
        }
        try {
            String codigo = mensagem[2];
            String cpf = mensagem[3];
            projetoPesquisaPersistence.removeParticipante(codigo, cpf);
            return "Participante removido com sucesso";
        } catch (NumberFormatException e) {
            return "Código do projeto inválido";
        } catch (IllegalStateException e) {
            return e.getMessage(); // Para o caso de tentar remover o responsável
        } catch (IllegalArgumentException e) {
            return e.getMessage(); // Para projeto ou participante não encontrado
        } catch (Exception e) {
            return "Erro inesperado: " + e.getMessage();
        }
    }
}
