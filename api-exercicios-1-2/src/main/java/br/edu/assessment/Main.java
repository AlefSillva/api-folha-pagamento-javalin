package br.edu.assessment;

import static br.edu.assessment.Api.criarApi;

public class Main {
    public static void main(String[] args) {
        //----------------------------------------------------------
        // Criando a instância do Javalin e configurando a rota incial
        criarApi().start(7000);
        //----------------------------------------------------------

    }
}