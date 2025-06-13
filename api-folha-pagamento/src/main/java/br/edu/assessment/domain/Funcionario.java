package br.edu.assessment.domain;

public class Funcionario {
    private String id;
    private long matricula;
    private String nome;
    private String cargo;
    private String tipo;

    public Funcionario() {}

    public Funcionario(long matricula, String nome, String cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipo() {
        return this instanceof Mensalista ? "Mensalista" : "Funcionario";
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
