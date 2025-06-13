package br.edu.assessment.domain;

public class Mensalista extends Funcionario {
    private double salario;

    public Mensalista() {}

    public Mensalista(long matricula, String nome, String cargo, double salario) {
        super(matricula, nome, cargo);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
