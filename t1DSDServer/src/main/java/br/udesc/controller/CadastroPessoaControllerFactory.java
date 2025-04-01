package br.udesc.controller;

import br.udesc.model.Aluno;
import br.udesc.model.Pessoa;
import br.udesc.model.Professor;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory para criar e gerenciar instâncias de controllers de cadastro.
 */
public class CadastroPessoaControllerFactory {

    private static final Map<Class<?>, CadastroPessoaController<?>> INSTANCES = new HashMap<>();

    /**
     * Retorna a instância de controller para o tipo especificado.
     * @param <T> Tipo de Pessoa
     * @param clazz Classe do tipo de Pessoa
     * @return Instância de controller
     */
    @SuppressWarnings("unchecked")
    public static <T extends Pessoa> CadastroPessoaController<T> getController(Class<T> clazz) {
        if (!INSTANCES.containsKey(clazz)) {
            CadastroPessoaController<T> controller;

            if (clazz.equals(Pessoa.class)) {
                controller = (CadastroPessoaController<T>) new CadastroPessoaControllerImpl();
            } else if (clazz.equals(Aluno.class)) {
                controller = (CadastroPessoaController<T>) new CadastroAlunoControllerImpl();
            } else if (clazz.equals(Professor.class)) {
                controller = (CadastroPessoaController<T>) new CadastroProfessorControllerImpl();
            } else {
                throw new IllegalArgumentException("Tipo de Pessoa não suportado: " + clazz.getName());
            }

            INSTANCES.put(clazz, controller);
        }

        return (CadastroPessoaController<T>) INSTANCES.get(clazz);
    }

    private CadastroPessoaControllerFactory() {}
}