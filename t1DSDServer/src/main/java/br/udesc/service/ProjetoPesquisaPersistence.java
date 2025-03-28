package br.udesc.service;

import br.udesc.model.Pessoa;
import br.udesc.model.ProjetoPesquisa;

import java.util.ArrayList;
import java.util.List;

public class ProjetoPesquisaPersistence {

    private static ProjetoPesquisaPersistence instance;

    private static List<ProjetoPesquisa> projetosList;
    private Integer contadorCodigo;

    private ProjetoPesquisaPersistence() {
        projetosList = new ArrayList<ProjetoPesquisa>();
        contadorCodigo = 0;
    }

    public static synchronized ProjetoPesquisaPersistence getInstance() {
        if (instance == null) {
            instance = new ProjetoPesquisaPersistence();
        }
        return instance;
    }

    public void insert(ProjetoPesquisa projeto) {
        projeto.setCodigo(++contadorCodigo);
        projeto.addParticipante(projeto.getResponsavel());
        projetosList.add(projeto);
    }

    public String update(ProjetoPesquisa projeto) {
        for (ProjetoPesquisa p : projetosList) {
            if (p.getCodigo().equals(projeto.getCodigo())) {
                p.setDescricao(projeto.getDescricao());
                p.setNomeGrupo(projeto.getNomeGrupo());
                p.setResponsavel(projeto.getResponsavel());
                return "Projeto atualizado com sucesso.";
            }
        }
        return "Projeto não encontrado.";
    }

    public String get(Integer codigo) {
        if (projetosList.isEmpty()) return "Sem projetos cadastrados";

        for (ProjetoPesquisa projeto : projetosList) {
            if (projeto.getCodigo().equals(codigo)) {
                return projeto.toString();
            }
        }
        return "Projeto não encontrado.";
    }

    public String delete(Integer codigo) {
        if (projetosList.isEmpty()) return "Sem projetos cadastrados";

        for (ProjetoPesquisa projeto : projetosList) {
            if (projeto.getCodigo().equals(codigo)) {
                projetosList.remove(projeto);
                return "Projeto removido com sucesso.";
            }
        }

        return "Projeto não encontrado";
    }

    public Integer getContadorCodigo() {
        return contadorCodigo;
    }

    public void addParticipante(String codigo, String cpf) {
        for (ProjetoPesquisa pj : projetosList) {
            if (pj.getCodigo().equals(Integer.parseInt(codigo))) {
                PessoaPersistence pessoaPersistence =  PessoaPersistence.getInstance();
                for(Pessoa ps : pessoaPersistence.getPessoaList()){
                    if(ps.getCpf().equals(cpf)){
                        pj.addParticipante(ps);
                    }
                }
            }
        }
    }

    public void removeParticipante(String codigo, String cpf) {
        for (ProjetoPesquisa pj : projetosList) {
            if (pj.getCodigo().equals(Integer.parseInt(codigo))) {
                PessoaPersistence pessoaPersistence = PessoaPersistence.getInstance();
                for(Pessoa ps : pessoaPersistence.getPessoaList()){
                    if(ps.getCpf().equals(cpf) && !ps.equals(pj.getResponsavel())){
                        pj.removeParticipante(ps);
                    }
                }
            }
        }
    }

    public String list() {
        return this.toString();
    }

    @Override
    public String toString() {
        int tamanho = projetosList.isEmpty() ? 0 : projetosList.size();
        if (tamanho == 0) return "0";

        StringBuilder retorno = new StringBuilder(tamanho + "\n");
        for (ProjetoPesquisa projeto : projetosList) {
            retorno.append(projeto.toString()).append("\n");
        }
        return retorno.toString();
    }
}
