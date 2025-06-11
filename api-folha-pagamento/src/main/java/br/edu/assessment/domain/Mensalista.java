package br.edu.assessment.domain;

public class Mensalista {
    private int id;
    private String nome;

    public Mensalista() {
        // Construtor Default para o jackson
    }

    public Mensalista(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
