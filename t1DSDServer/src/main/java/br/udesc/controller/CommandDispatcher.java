package br.udesc.controller;

import br.udesc.model.Aluno;
import br.udesc.model.Pessoa;
import br.udesc.model.Professor;

/**
 * Dispatcher central para comandos de pessoa.
 */
public class CommandDispatcher {

    private static CommandDispatcher instance;

    private CadastroPessoaController<Pessoa> pessoaController;
    private CadastroPessoaController<Aluno> alunoController;
    private CadastroPessoaController<Professor> professorController;

    private CommandDispatcher() {
        pessoaController = CadastroPessoaControllerFactory.getController(Pessoa.class);
        alunoController = CadastroPessoaControllerFactory.getController(Aluno.class);
        professorController = CadastroPessoaControllerFactory.getController(Professor.class);
    }

    public static synchronized CommandDispatcher getInstance() {
        if (instance == null) {
            instance = new CommandDispatcher();
        }
        return instance;
    }

    /**
     * Processa um comando e direciona para o controller apropriado.
     * @param comando String com o comando completo
     * @return Resultado da operação ou mensagem de erro
     */
    public String processarComando(String comando) {
        if (comando == null || comando.isEmpty()) {
            return "Comando inválido";
        }

        String[] partes = comando.split(";");
        if (partes.length < 2) {
            return "Comando incompleto";
        }

        String tipo = partes[0].toUpperCase();
        String operacao = partes[1].toUpperCase();

        switch (tipo) {
            case "PESSOA":
                return executarOperacao(pessoaController, operacao, comando);

            case "ALUNO":
                return executarOperacao(alunoController, operacao, comando);

            case "PROFESSOR":
                return executarOperacao(professorController, operacao, comando);

            default:
                return "Tipo de pessoa desconhecido: " + tipo;
        }
    }

    private String executarOperacao(CadastroPessoaController<?> controller, String operacao, String comando) {
        switch (operacao) {
            case "INSERT":
                controller.insert(comando);
                return "Inserção realizada";

            case "UPDATE":
                return controller.update(comando);

            case "GET":
                return controller.get(comando);

            case "DELETE":
                return controller.delete(comando);

            case "LIST":
                return controller.list();

            default:
                return "Operação desconhecida: " + operacao;
        }
    }
}