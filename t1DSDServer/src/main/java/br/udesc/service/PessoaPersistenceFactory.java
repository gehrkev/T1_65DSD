package br.udesc.service;

import br.udesc.model.Pessoa;
import br.udesc.model.Professor;
import br.udesc.model.Aluno;
import java.util.HashMap;
import java.util.Map;

public class PessoaPersistenceFactory {

    private static final Map<Class<?>, PessoaPersistence<?>> INSTANCES = new HashMap<>();

    /**
     * Retorna a instância de persistência para o tipo especificado.
     * @param <T> Tipo de Pessoa
     * @param clazz Classe do tipo de Pessoa
     * @return Instância de persistência
     */
    @SuppressWarnings("unchecked")
    public static <T extends Pessoa> PessoaPersistence<T> getPersistence(Class<T> clazz) {
        if (!INSTANCES.containsKey(clazz)) {
            PessoaPersistence<T> persistence;

            if (clazz.equals(Pessoa.class)) {
                persistence = (PessoaPersistence<T>) new PessoaPersistenceImpl();
            } else if (clazz.equals(Professor.class)) {
                persistence = (PessoaPersistence<T>) new ProfessorPersistenceImpl();
            } else if (clazz.equals(Aluno.class)) {
                persistence = (PessoaPersistence<T>) new AlunoPersistenceImpl();
            } else {
                throw new IllegalArgumentException("Tipo de Pessoa não suportado: " + clazz.getName());
            }

            INSTANCES.put(clazz, persistence);
        }

        return (PessoaPersistence<T>) INSTANCES.get(clazz);
    }

    private PessoaPersistenceFactory() {}
}