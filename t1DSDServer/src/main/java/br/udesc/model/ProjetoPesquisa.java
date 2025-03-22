package br.udesc.model;

import java.util.ArrayList;
import java.util.List;

public class ProjetoPesquisa {

    private List<Pessoa> participantes = new ArrayList<Pessoa>();

    private String nomeGrupo;

    private Integer codigo = -1; // Se houver algum Projeto com codigo -1 há um erro de inser

    private String descricao;

    private Pessoa responsavel;

    public ProjetoPesquisa(String nomeGrupo/*, Integer codigo*/, String descricao, Pessoa responsavel) {
        this.nomeGrupo = nomeGrupo;
//        this.codigo = codigo;
        this.descricao = descricao;
        this.responsavel = responsavel;
    }

    public List<Pessoa> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Pessoa> participantes) {
        this.participantes = participantes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public void addParticipante(Pessoa p) {
        if(!participantes.contains(p)) {
            participantes.add(p);
        }
    }

    public void removeParticipante(Pessoa p) {
        if(participantes.contains(p)) {
            participantes.remove(p);
        }
    }

    private String participantesToString(List<Pessoa> participantes) {
        StringBuilder nomesParticipantes = new StringBuilder();
        for (Pessoa p : participantes) {
            nomesParticipantes.append(p.getNome()).append(";");
        }
        return nomesParticipantes.toString();
    }
    @Override
    public String toString() {
        return codigo + ";" + nomeGrupo + ";" + descricao + ";" + responsavel.getNome() + ";" + participantesToString(participantes);
    }
}
