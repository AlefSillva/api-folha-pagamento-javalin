package br.edu.assessment.application;

import br.edu.assessment.infrastructure.FuncionarioService;
import io.javalin.Javalin;

public class Api {
    public static Javalin criarServidor() {
        Javalin server = Javalin.create();

        new FuncionarioController(server, new FuncionarioService());
        new BasicController(server);

        return server;
    }

    public static void main(String[] args) {
        //------------------------------------------------
        Javalin server = criarServidor();
        server.start(7000);
        //------------------------------------------------

    }
}
