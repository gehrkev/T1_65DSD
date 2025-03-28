package br.udesc.model;

public class Aluno extends Pessoa{

    private String matricula;

    public Aluno() {
        super();
        this.matricula = "";
    }

    public Aluno(String cpf, String nome, String endereco, String matricula) {
        super(cpf, nome, endereco);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return  super.toString() + ";" + matricula;
    }
}
